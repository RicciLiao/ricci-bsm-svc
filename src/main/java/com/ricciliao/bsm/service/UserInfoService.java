package com.ricciliao.bsm.service;

import com.ricciliao.bsm.pojo.UserInfoPo;

public interface UserInfoService {
    Integer createUser(UserInfoPo userInfoPo);
    UserInfoPo loginUser(UserInfoPo userInfoPo);
}
