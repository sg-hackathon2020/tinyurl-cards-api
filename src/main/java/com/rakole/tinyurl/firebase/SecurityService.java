package com.rakole.tinyurl.firebase;

import com.rakole.tinyurl.model.security.Credentials;
import com.rakole.tinyurl.model.security.SecurityProperties;
import com.rakole.tinyurl.model.security.User;
import com.rakole.tinyurl.utils.CookieUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService {


    final
    CookieUtils cookieUtils;

    final
    SecurityProperties securityProps;

    public SecurityService(CookieUtils cookieUtils, SecurityProperties securityProps) {
        this.cookieUtils = cookieUtils;
        this.securityProps = securityProps;
    }

    public User getUser() {
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        return userPrincipal;
    }

    public Credentials getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Credentials) securityContext.getAuthentication().getCredentials();
    }


    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization)) {
            if (authorization.startsWith("Bearer ")) {
                bearerToken = authorization.substring(7);
            } else {
                bearerToken = authorization;
            }
        }
        if (bearerToken != null)
            bearerToken = bearerToken.replace("\"", "");

        return bearerToken;
    }

}
