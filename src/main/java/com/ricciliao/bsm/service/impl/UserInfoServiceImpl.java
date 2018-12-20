package com.ricciliao.bsm.service.impl;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.common.MD5Util;
import com.ricciliao.bsm.mapper.dao.UserInfoMapper;
import com.ricciliao.bsm.pojo.UserInfoPo;
import com.ricciliao.bsm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper = null;

    @Override
    public Map createUser(UserInfoPo poFromCon) {
        String strMD5 = null;
        String strGuid = null;
        String strPath = null;
        Integer intSpResult = null;
        Map<String, Object> mapToSP = null;
        Map<String, Object> mapForErr = null;
        Map<String, Object> mapToCon = null;

        try {
            strGuid = Common.generateGUID();
            strPath = Constants.SERVER_PATH + strGuid;
            mapToCon = new HashMap<String, Object>();
            if (Common.createItem(strPath)) {
                strMD5 = MD5Util.Encryption(poFromCon.getUserPassword());
                mapToSP = new HashMap<String, Object>();
                mapToSP.put("inUserGuid", strGuid);
                mapToSP.put("inUserPath", strPath);
                mapToSP.put("inUserPassword", strMD5);
                mapToSP.put("inUserName", poFromCon.getUserName());
                mapToSP.put("inUserEmail", poFromCon.getUserEmail());
                mapToSP.put("inUserPhone", poFromCon.getUserPhone());
                userInfoMapper.createUser(mapToSP);
                intSpResult = Common.convertToInteger(mapToSP.get("outResult"));

                if (intSpResult > 0) {
                    mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.SUCCESS);
                } else {
                    mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
                    mapForErr = new HashMap<String, Object>();
                    if (Common.compareIntergers(intSpResult, Constants.SP_NAME_EXIST)) {
                        mapForErr.put(Constants.NAME_INFO, Constants.EXISTED);
                    } else if (Common.compareIntergers(intSpResult, Constants.SP_PHONE_EXIST)) {
                        mapForErr.put(Constants.PHONE_INFO, Constants.EXISTED);
                    } else if (Common.compareIntergers(intSpResult, Constants.SP_NAME_PHONE_BOTH_EXIST)) {
                        mapForErr.put(Constants.NAME_INFO, Constants.EXISTED);
                        mapForErr.put(Constants.PHONE_INFO, Constants.EXISTED);
                    } else if (Common.compareIntergers(intSpResult, Constants.SP_ERROR)) {
                        mapForErr.put(Constants.SYS_INFO, Constants.ERROR);
                    }

                    mapToCon.put(Constants.AJAX_COMMON_ERROR, mapForErr);
                }
            } else {
                mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mapToCon;
        }

    }

    @Override
    public Map loginUser(UserInfoPo poFromCon) {
        String strMD5 = null;
        UserInfoPo poFromDB = null;
        Map<String, Object> mapToSQL = null;
        Map<String, Object> mapToCon = null;
        try {
            mapToCon = new HashMap<String, Object>();
            strMD5 = MD5Util.Encryption(poFromCon.getUserPassword());
            mapToSQL = new HashMap<String, Object>();
            mapToSQL.put("userPhone", poFromCon.getUserPhone());
            mapToSQL.put("userPassword", strMD5);
            poFromDB = userInfoMapper.loginUser(mapToSQL);
            if (poFromDB != null && poFromDB.getId() != 0) {
                mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.SUCCESS);
                mapToCon.put(Constants.SUCCESS, poFromDB);
            } else {
                mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapToCon;
    }

}
