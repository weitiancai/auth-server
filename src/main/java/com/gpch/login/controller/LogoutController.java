package com.gpch.login.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class LogoutController {

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("here is exit");
        // token can be revoked here if needed  wonderful!!!
        // request obtain the token information and so on
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            response.sendRedirect(request.getHeader("referer"));
            // 从哪来回哪去，经过上一步肯定还要验证的
            // in ths case the header's referrer is 8080/notes
            // and because of the revoking token this url is not authenticated so it redirect to auth-server again
            // again key in username and password.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

