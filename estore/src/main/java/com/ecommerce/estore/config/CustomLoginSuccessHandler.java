package com.ecommerce.estore.config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws java.io.IOException {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String loginType = request.getParameter("loginType");

        if ("admin".equals(loginType) && role.equals("ROLE_ADMIN")) {
            response.sendRedirect("/admin/dashboard");
        } else if ("user".equals(loginType) && role.equals("ROLE_USER")) {
            response.sendRedirect("/products");
        } else {
            // Invalid login type for the given role
            response.sendRedirect("/login?error=invalidRole");
        }
    }
}
