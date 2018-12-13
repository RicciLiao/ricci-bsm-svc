package com.ricciliao.bsm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageCon {

    @GetMapping("/")
    public ModelAndView defaultPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }

}
