/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.plugin.inteceptor;

import org.elastos.constants.Auth;
import org.elastos.constants.RetCode;
import org.elastos.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;


@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestRecord(request);

        if (!auth(request, handler)) {
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(new ServerResponse().setState(RetCode.ERROR).setMsg("手机未验证").toJsonString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    private boolean auth(HttpServletRequest request, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //不是验证声明方法直接通过
        if (method.getAnnotation(org.elastos.annotation.Auth.class) == null) {
            return true;
        }

        if (authBySession(request)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean authBySession(HttpServletRequest request) {
        //Get user id from session
        Long uid = (Long) request.getSession().getAttribute(Auth.USER_ID);
        if (null != uid) {
            request.setAttribute(Auth.USER_ID, uid);
            return true;
        } else {
            return false;
        }
    }

    private void requestRecord(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String reqBody = "";
        if(RequestMethod.POST.toString().equals(method)){
            InputStream is = request.getInputStream();
            int index = -1;
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((index = is.read(buf)) != -1) {
                baos.write(buf, 0, index);
            }
            reqBody = baos.toString();
            request.setAttribute("reqBody", reqBody);
        }
        logger.debug("method = {},reqBody = {},requestURI = {},queryString={}", method, reqBody, requestURI, queryString);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
