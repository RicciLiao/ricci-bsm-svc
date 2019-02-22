package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.UserInfoPo;
import com.ricciliao.bsm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/userAction")
public class UserCon {

    @Autowired
    private UserInfoService userInfoService = null;

    @Autowired
    private HttpServletRequest g_request;

    @Autowired
    private MessageSource messageSource;

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
        //String jSessionId = null;
        UserInfoPo poFromSer = null;
        HttpSession curSession = null;
        Map<String, Object> mapResult = null;
        try {
            curSession = g_request.getSession();
            //jSessionId = curSession.getId();

            curSession.setAttribute(Constants.AJAX_COMMON_RESULT, Constants.PENDING);

            mapResult = userInfoService.loginUser(userInfoPo);

            if (mapResult.get(Constants.AJAX_COMMON_RESULT).equals(Constants.SUCCESS)) {
                poFromSer = (UserInfoPo) mapResult.get(Constants.SUCCESS);
                curSession.setAttribute(Constants.AJAX_COMMON_RESULT, Constants.SUCCESS);
                //curSession.setAttribute(jSessionId, poFromSer);
                curSession.setAttribute(Constants.USER_INFO_PO, poFromSer);
                mapResult.remove(Constants.SUCCESS);
                curSession.removeAttribute(Constants.AJAX_COMMON_RESULT);
            } else {
                curSession.setAttribute(Constants.AJAX_COMMON_RESULT, Constants.PENDING);
            }

            ajaxResult = Common.mapToJson(mapResult);
            System.out.println(messageSource.getMessage("signIn-success", null, LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            curSession.setAttribute(Constants.AJAX_COMMON_RESULT, Constants.PENDING);
            mapResult.put(Constants.AJAX_COMMON_RESULT, Constants.ERROR);
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }


}
