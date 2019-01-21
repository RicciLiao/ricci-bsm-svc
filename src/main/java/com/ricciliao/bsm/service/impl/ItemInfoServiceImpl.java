package com.ricciliao.bsm.service.impl;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.mapper.dao.ItemInfoMapper;
import com.ricciliao.bsm.pojo.ItemInfoPo;
import com.ricciliao.bsm.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ItemInfoServiceImpl implements ItemInfoService {

    @Autowired
    private ItemInfoMapper itemInfoMapper = null;

    @Override
    public Map save(ItemInfoPo itemInfoPo, String itemContent, String userPath) {
        Map<String, Object> mapToSQL = new HashMap<String, Object>();
        if(Common.isNullOrSpace(itemInfoPo.getItemGuid())){
            String strGUID = Common.generateGUID();

            mapToSQL.put("itemGuid", strGUID);
            itemInfoMapper.createItem(mapToSQL);
        } else {

        }
        return null;
    }
}
