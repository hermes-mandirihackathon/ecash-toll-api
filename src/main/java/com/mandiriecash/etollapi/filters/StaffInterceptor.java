package com.mandiriecash.etollapi.filters;

import com.google.gson.Gson;
import com.mandiriecash.etollapi.exceptions.staffs.TokenStaffNotFoundException;
import com.mandiriecash.etollapi.exceptions.staffs.TokenStaffNotProvidedException;
import com.mandiriecash.etollapi.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    StaffService staffService;

    @Autowired
    Gson gson;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //filter except login
        String path = request.getRequestURI();
        if (!path.startsWith("/staffs/login")){
            String token = request.getParameter("token");
            if (token == null){
                throw new TokenStaffNotProvidedException();
            } else {
                staffService.getStaffByToken(token);
            }
        }
        return true;
    }
}
