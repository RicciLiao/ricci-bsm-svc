package com.ricciliao.bsm.service.impl;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.mapper.dao.ItemInfoMapper;
import com.ricciliao.bsm.pojo.ItemInfoPo;
import com.ricciliao.bsm.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Autowired
    private ItemInfoMapper itemInfoMapper = null;

    @Override
    public Map save(ItemInfoPo itemInfoPo, String itemContent, String userPath) {
        Map<String, Object> mapToCon = new HashMap<>();

        if (Common.isNullOrSpace(itemInfoPo.getItemGuid())) {
            String strGUID = Common.generateGUID();
            String filePath = userPath + File.separator + strGUID;
            File file = new File(filePath);
            if (Common.writeItem(file, itemContent)) {
                itemInfoPo.setItemGuid(strGUID);
                itemInfoPo.setItemPath(filePath);
                itemInfoPo.setItemCreateDate(new Date(file.lastModified()));
                itemInfoMapper.createItem(itemInfoPo);
                mapToCon.put(Constants.AJAX_COMMON_RESULT, itemInfoPo.getItemGuid());
            } else {
                mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            }
        } else {
            File tarFile = new File(itemInfoPo.getItemPath());
            File bakFile = new File(Constants.SERVER_TEMP_BAK_PATH + itemInfoPo.getItemGuid());
            if (Common.copyItem(tarFile, bakFile)) {
                if (Common.writeItem(tarFile, itemContent)) {
                    itemInfoPo.setItemLastModifiedDate(new Date(tarFile.lastModified()));
                    itemInfoMapper.updateItem(itemInfoPo);
                    bakFile.delete();
                    mapToCon.put(Constants.AJAX_COMMON_RESULT, itemInfoPo.getItemGuid());
                } else {
                    mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
                }
            } else {
                mapToCon.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            }
        }
        return mapToCon;
    }

    @Override
    public ItemInfoPo getItemByGuid(String itemGuid) {
        ItemInfoPo itemInfoPo = itemInfoMapper.getItemByGuid(itemGuid);
        if(itemInfoPo != null){
            File file = new File(itemInfoPo.getItemPath());
            String itemContent = Common.readItem(file);
            itemInfoPo.setItemContent(itemContent);
        }
        return itemInfoPo;

    }

    @Override
    public List<ItemInfoPo> getItemListForIndex() {
        List<ItemInfoPo> itemInfoPoList = itemInfoMapper.getItemListForIndex();
        return itemInfoPoList;
    }
}
