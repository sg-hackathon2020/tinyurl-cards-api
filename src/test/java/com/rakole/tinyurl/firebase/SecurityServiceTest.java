package com.rakole.tinyurl.firebase;

import com.rakole.tinyurl.model.security.Credentials;
import com.rakole.tinyurl.model.security.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {
    @Mock
    HttpServletRequest request;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Mock
    Credentials credentials;


    @InjectMocks
    SecurityService securityService;

    @Test
    @DisplayName("If the token header type is Authorization," +
            " and the token starts with Bearer," +
            " then remove Bearer and remove unwanted \"")
    void getBearerToken() {
        //given
        given(request.getHeader("Authorization")).willReturn("Bearer abcdefg");

        //when
        String bearerToken = securityService.getBearerToken(request);

        //then
        assertFalse(bearerToken.contains("Bearer"));
        assertFalse(bearerToken.contains("\""));
        assertEquals("abcdefg", bearerToken);
    }

    @Test
    @DisplayName("If the token header type is Authorization," +
            " and the token does not start with bearer")
    void getBearerTokenWithoutBearer() {
        //given
        given(request.getHeader("Authorization")).willReturn("xxxxabcdefg");

        //when
        String bearerToken = securityService.getBearerToken(request);

        //then
        assertFalse(bearerToken.contains("Bearer"));
        assertFalse(bearerToken.contains("\""));
        assertEquals("xxxxabcdefg", bearerToken);
    }


    @Test
    @DisplayName("When there is no authorization header present, the method should return null")
    void whenNoAuthorizationHeader() {
        given(request.getHeader("Authorization")).willReturn(null);

        //when
        String bearerToken = securityService.getBearerToken(request);

        //then
        assertNull(bearerToken);
    }

    @Test
    @DisplayName("When getUser is called and security context is present, we should get back user")
    void testGetUser() {
        //given
        User user = User.builder().email("xyz@gmail.com").issuer("YES").build();
        given(securityContext.getAuthentication()).willReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        given(securityContext.getAuthentication().getPrincipal()).willReturn(user);


        //when
        User contextUser = securityService.getUser();

        //then
        Assertions.assertEquals(user, contextUser);
        then(securityContext).should(times(2)).getAuthentication();

    }

    @Test
    void testGetCredentials() {
        //given
        given(securityContext.getAuthentication()).willReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        given(securityContext.getAuthentication().getCredentials()).willReturn(credentials);

        //when
        Credentials credentials = securityService.getCredentials();

        //then
        then(securityContext).should(times(2)).getAuthentication();

    }

}

