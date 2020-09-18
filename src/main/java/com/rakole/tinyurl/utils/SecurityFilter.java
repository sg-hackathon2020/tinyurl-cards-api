package com.rakole.tinyurl.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.rakole.tinyurl.firebase.SecurityService;
import com.rakole.tinyurl.model.security.Credentials;
import com.rakole.tinyurl.model.security.SecurityProperties;
import com.rakole.tinyurl.model.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    final
    SecurityService securityService;

    final
    SecurityProperties restSecProps;

    final
    CookieUtils cookieUtils;

    final
    SecurityProperties securityProps;

    public SecurityFilter(SecurityService securityService, SecurityProperties restSecProps, CookieUtils cookieUtils, SecurityProperties securityProps) {
        this.securityService = securityService;
        this.restSecProps = restSecProps;
        this.cookieUtils = cookieUtils;
        this.securityProps = securityProps;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) {
        String sessionCookieValue = null;
        FirebaseToken decodedToken = null;
        Credentials.CredentialType type = null;
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
        try {
            if (sessionCookie != null) {
                sessionCookieValue = sessionCookie.getValue();
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(sessionCookieValue,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;
            } else if (!strictServerSessionEnabled) {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    type = Credentials.CredentialType.ID_TOKEN;
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            log.error("Firebase Exception:: ", e.getLocalizedMessage());
        }
        User user = firebaseTokenToUserConverter(decodedToken);
        if (user != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                    new Credentials(type, decodedToken, token, sessionCookieValue), null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public User firebaseTokenToUserConverter(FirebaseToken decodedToken) {
        User user = null;
        if (decodedToken != null) {
            user = new User();
            user.setUid(decodedToken.getUid());
            user.setName(decodedToken.getName());
            user.setEmail(decodedToken.getEmail());
            user.setPicture(decodedToken.getPicture());
            user.setIssuer(decodedToken.getIssuer());
            user.setEmailVerified(decodedToken.isEmailVerified());
        }
        return user;
    }

}
