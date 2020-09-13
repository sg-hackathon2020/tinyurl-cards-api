package com.rakole.tinyurl.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CookieUtilsTest {

    @Mock
    WebUtils webUtils;

    @Mock
    HttpServletRequest httpServletRequest;

    @InjectMocks
    CookieUtils cookieUtils;

    @Test
    void getCookie() {
        //given
        given(WebUtils.getCookie(httpServletRequest, "test")).willReturn(new Cookie("test", "testCookie"));

        //when
        Cookie returnedCookie = cookieUtils.getCookie("test");

        //then
        assertNotNull(returnedCookie);
    }

    @Test
    void setCookie() {
    }

    @Test
    void setSecureCookie() {
    }

    @Test
    void testSetSecureCookie() {
    }

    @Test
    void deleteSecureCookie() {
    }

    @Test
    void deleteCookie() {
    }
}