package com.ricciliao.bsm.service.impl;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.common.MD5Util;
import com.ricciliao.bsm.mapper.dao.UserInfoMapper;
import com.ricciliao.bsm.pojo.UserInfoPo;
import com.ricciliao.bsm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper = null;


    @Override
    public Integer createUser(UserInfoPo poFromCon) {
        String strGuid = null;
        String strPath = null;
        String strMD5 = null;
        Map<String, Object> mapToSP = null;
        Integer result = null;

        try {
            strGuid = Common.generateGUID();
            strPath = Constants.SERVER_PATH + strGuid;
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
                result = Common.convertToInteger(mapToSP.get("outResult"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    @Override
    public UserInfoPo loginUser(UserInfoPo poFromCon) {
        String strMD5 = null;
        UserInfoPo poFromDB = null;
        Map<String, Object> mapToSQL = null;

        try {
            strMD5 = MD5Util.Encryption(poFromCon.getUserPassword());
            mapToSQL = new HashMap<String, Object>();
            mapToSQL.put("userPhone", poFromCon.getUserPhone());
            mapToSQL.put("userPassword", strMD5);
            poFromDB = userInfoMapper.loginUser(mapToSQL);
            System.out.println(poFromDB.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return poFromDB;
    }
}
