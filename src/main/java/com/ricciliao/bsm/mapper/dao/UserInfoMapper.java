package com.ricciliao.bsm.mapper.dao;

import com.ricciliao.bsm.pojo.UserInfoPo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserInfoMapper {
    Map createUser(Map map);
    UserInfoPo loginUser(Map map);
}
