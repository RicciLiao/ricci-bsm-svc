package com.ricciliao.bsm.service;

import com.ricciliao.bsm.pojo.UserInfoPo;

import java.util.Map;

public interface UserInfoService {
    Map createUser(UserInfoPo userInfoPo);
    Map loginUser(UserInfoPo userInfoPo);
}
