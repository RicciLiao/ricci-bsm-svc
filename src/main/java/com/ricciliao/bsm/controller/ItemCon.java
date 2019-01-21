package com.ricciliao.bsm.controller;


import com.ricciliao.bsm.pojo.ItemInfoPo;
import com.ricciliao.bsm.pojo.UserInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/itemAction")
public class ItemCon {

    @Autowired
    private HttpServletRequest g_request;

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody Map<String, String> params, HttpServletRequest request){
        UserInfoPo userInfoPo = null;
        HttpSession curSession = null;
        ItemInfoPo itemInfoPoToSer = null;

        try {
            curSession = request.getSession();
            userInfoPo = (UserInfoPo)curSession.getAttribute(curSession.getId());

            itemInfoPoToSer = new ItemInfoPo();
            itemInfoPoToSer.setItemName(params.get("mdTitle"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "";
    }
}
