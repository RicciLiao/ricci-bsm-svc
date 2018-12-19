package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.UserInfoPo;
import com.ricciliao.bsm.service.UserInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userAction")
public class UserCon {

    @Autowired
    private UserInfoService userInfoService = null;

    @PostMapping("/signUp")
    @ResponseBody
    public String signUp(@RequestBody UserInfoPo userInfoPo) {
        String ajaxResult = null;
        Map<String, Object> mapResult = null;
        try {
            mapResult = userInfoService.createUser(userInfoPo);
            ajaxResult = Common.mapToJson(mapResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }

    @PostMapping("/signIn")
    @ResponseBody
    public String signIn(@RequestBody UserInfoPo userInfoPo) {
        String ajaxResult = null;
        Map<String, Object> mapResult = null;
        try {
            mapResult = userInfoService.loginUser(userInfoPo);
            ajaxResult = Common.mapToJson(mapResult);
        } catch (Exception e) {
            mapResult.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }

}
