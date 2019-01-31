package com.ricciliao.bsm.controller;


import com.ricciliao.bsm.common.Common;
import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.ItemInfoPo;
import com.ricciliao.bsm.pojo.UserInfoPo;
import com.ricciliao.bsm.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/itemAction")
public class ItemCon {

    @Autowired
    private HttpServletRequest g_request;

    @Autowired
    private ItemInfoService itemInfoService = null;

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody Map<String, String> params) {
        String ajaxResult = null;
        UserInfoPo userInfoPo = null;
        HttpSession curSession = null;
        ItemInfoPo itemInfoPoToSer = null;
        Map<String, Object> mapResult = null;

        try {
            curSession = g_request.getSession();
            userInfoPo = (UserInfoPo) curSession.getAttribute(Constants.USER_INFO_PO);
            itemInfoPoToSer = new ItemInfoPo();
            itemInfoPoToSer.setItemName(params.get("mdTitle"));
            itemInfoPoToSer.setUserId(userInfoPo.getId());
            itemInfoPoToSer.setItemAnnotation(params.get("mdAnnotation"));
            if (!Common.isNullOrSpace(params.get("mdGuid"))) {
                itemInfoPoToSer.setItemGuid(params.get("mdGuid"));
                itemInfoPoToSer.setItemPath(userInfoPo.getUserPath() + File.separator + params.get("mdGuid"));
            }

            mapResult = itemInfoService.save(itemInfoPoToSer, params.get("mdContent"), userInfoPo.getUserPath());
            ajaxResult = Common.mapToJson(mapResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ajaxResult;
        }
    }

    @PostMapping("/getItemList")
    @ResponseBody
    public List<ItemInfoPo> getItemList(@RequestBody Map<String, String> params) {
        List<ItemInfoPo> itemInfoPoListToSer = null;
        Map<String, Object> mapResult = null;

        try {
            if(params.get("type").equals("index")){
                itemInfoPoListToSer = itemInfoService.getItemListForIndex();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return itemInfoPoListToSer;
        }
    }

    @RequestMapping("/mdEditor/{itemGuid}")
    public ModelAndView getItemByGuid(@PathVariable String itemGuid, RedirectAttributes ras) {
        ItemInfoPo itemInfoPo = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/bsm/mdEditor");

        try {
            if (!Common.isNullOrSpace(itemGuid)) {
                itemInfoPo = itemInfoService.getItemByGuid(itemGuid);
                if(itemInfoPo != null){
                    ras.addFlashAttribute("mdTitle", itemInfoPo.getItemName());
                    ras.addFlashAttribute("mdContent", itemInfoPo.getItemContent());
                    ras.addFlashAttribute("mdGuid", itemInfoPo.getItemGuid());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }
}
