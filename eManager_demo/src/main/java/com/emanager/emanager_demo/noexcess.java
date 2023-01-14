package com.emanager.emanager_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.AccessDeniedException;

public class noexcess implements AccessDeniedHandler {

    private static final Logger LOG = LoggerFactory.getLogger(noexcess.class);


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

            LOG.info("User '" + authentication.getName() +
                    "' attempted to access the URL: " +
                    request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath()+ "/exception");
    }

}
