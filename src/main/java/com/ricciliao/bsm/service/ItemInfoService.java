package com.ricciliao.bsm.service;

import com.ricciliao.bsm.pojo.ItemInfoPo;

import java.util.Map;

public interface ItemInfoService {
    Map save(ItemInfoPo itemInfoPo, String itemContent, String userPath);
    ItemInfoPo getItemByGuid(String itemGuid);
}
