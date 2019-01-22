package com.ricciliao.bsm.interceptor;

import com.ricciliao.bsm.common.Constants;
import com.ricciliao.bsm.pojo.UserInfoPo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ConcurrentModificationException;

public class PageConInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean result = false;
        Object objSession = null;
        HttpSession session = null;
        try {
            session = request.getSession();

            objSession = session.getAttribute(Constants.USER_INFO_PO);
            if(objSession != null && objSession instanceof UserInfoPo){
                result = true;
            } else {
                result = false;
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}