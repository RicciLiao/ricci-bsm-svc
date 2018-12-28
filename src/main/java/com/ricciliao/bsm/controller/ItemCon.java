package com.ricciliao.bsm.controller;


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

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody Map<String, String> params, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.getId();
        System.out.println(params);
        return "";
    }
}
