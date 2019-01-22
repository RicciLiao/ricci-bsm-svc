package com.ricciliao.bsm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageCon {

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

    @RequestMapping("/bsm/mdEditor")
    public ModelAndView mdEditorPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bsm/mdEditor");
        return mv;
    }

    @RequestMapping("/bsm/mdEditor/{itemGuid}")
    public ModelAndView mdEditorPageByItemGuid(@PathVariable String itemGuid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/itemAction/mdEditor/" + itemGuid);
        return mv;
    }

    @RequestMapping("/bsm/index")
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bsm/index");
        return mv;
    }

}
