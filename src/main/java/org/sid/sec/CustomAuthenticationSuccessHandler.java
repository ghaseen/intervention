package org.sid.sec;

import java.io.IOException;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	    
        if (roles.contains("ROLE_ADMIN")) {
        	
        	new DefaultRedirectStrategy().sendRedirect(request, response, "/admin/dashboard");
        } else if (roles.contains("ROLE_CLIENT")) {
        	new DefaultRedirectStrategy().sendRedirect(request, response,"/client/reclamationC");
        }
        else {
        	new DefaultRedirectStrategy().sendRedirect(request, response,"/technicien/interventionT");
        }
    }
		
	}


