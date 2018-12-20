package com.ricciliao.bsm.controller;


import com.ricciliao.bsm.pojo.UserInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PageCon {

    @Autowired
    private HttpServletRequest g_request;

    @GetMapping("/")
    public ModelAndView defaultPage() {
        ModelAndView mv = null;
        try {

            mv = new ModelAndView();
            mv.setViewName("main");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mv;
        }
    }

    @RequestMapping("/base")
    public ModelAndView basePage() {
        ModelAndView mv = null;
        String jSessionId = null;
        HttpSession curSession = null;
        UserInfoPo poFromSession = null;

        try {
            mv = new ModelAndView();
            curSession = g_request.getSession();
            jSessionId = curSession.getId();
            if (curSession.getAttribute(jSessionId) != null
                    && curSession.getAttribute(jSessionId) != null
                    && curSession.getAttribute(jSessionId) instanceof UserInfoPo) {

                poFromSession = (UserInfoPo) curSession.getAttribute(jSessionId);
                mv.addObject("message", poFromSession.getUserName());

                mv.setViewName("bsm/base");

            } else {
                mv.setViewName("redirect:/");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mv;
        }
    }

}
