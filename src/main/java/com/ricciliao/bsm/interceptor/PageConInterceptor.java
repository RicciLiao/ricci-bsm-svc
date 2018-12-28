package com.ricciliao.bsm.interceptor;

import com.ricciliao.bsm.pojo.UserInfoPo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageConInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean result = false;
        HttpSession session = null;
        Object objSession = null;
        UserInfoPo poFromSession = null;
        try {
            session = request.getSession();

            objSession = session.getAttribute(session.getId());
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