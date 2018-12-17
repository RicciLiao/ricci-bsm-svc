package com.ricciliao.bsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping("userAction")
public class UserCon {

    @GetMapping("/signUp")
    public ModelAndView signUp() {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mv;
        }


    }

}
