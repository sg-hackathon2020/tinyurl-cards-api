package com.rakole.tinyurl.firebase;

import com.google.firebase.auth.FirebaseToken;
import com.rakole.tinyurl.model.security.SecurityProperties;
import com.rakole.tinyurl.model.security.User;
import com.rakole.tinyurl.utils.CookieUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {

    @Mock
    SecurityService securityService;

    @Mock
    SecurityProperties securityProperties;

    @Mock
    CookieUtils cookieUtils;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain filterChain;

    @Mock
    FirebaseToken firebaseToken;

    @InjectMocks
    SecurityFilter securityFilter;


    @Test
    @DisplayName("Test if correct user is getting correlated")
    void firebaseTokenToUserConverter() {
        User user = User.builder().uid("myUid")
                .email("xyz@gmail.com").name("rhishi")
                .isEmailVerified(true).issuer("firebase").build();
        //given
        given(firebaseToken.getUid()).willReturn("myUid");
        given(firebaseToken.getName()).willReturn("rhishi");
        given(firebaseToken.getEmail()).willReturn("xyz@gmail.com");
        given(firebaseToken.getIssuer()).willReturn("firebase");
        given(firebaseToken.isEmailVerified()).willReturn(true);

        //when
        User userFromFirebaseToken = securityFilter.firebaseTokenToUserConverter(firebaseToken);

        //then
        assertEquals(user, userFromFirebaseToken);
    }

}