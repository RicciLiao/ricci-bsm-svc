package com.ricciliao.bsm.controller;

import com.ricciliao.bsm.pojo.UserPo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping("userAction")
public class UserCon {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "your name:");
        model.addAttribute("psw", "your psw:");
        model.addAttribute("submit", "submit");
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public UserPo login(@RequestBody UserPo userPo){
        return userPo;
    }

    @RequestMapping("/loginSuccess")
    public ModelAndView loginSuccess(String userFormJson){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginSuccess");
        JSONObject jsonObject = JSONObject.fromObject(userFormJson);
        UserPo userPo = new UserPo();
        userPo.setName(jsonObject.getString("name"));
        userPo.setPsw(jsonObject.getString("psw"));
        mv.addObject(userPo);
        return mv;
    }

}
