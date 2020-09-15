package com.rakole.tinyurl.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    void getCardDtoById() {
    }

    @Test
    void save() {
    }

    @Test
    @DisplayName("When google is pinged, the return code should be 200 and get a true")
    void checkUrlWorks() throws IOException {
        assertTrue(CardServiceImpl.checkUrlWorks("http://www.google.com"));
    }

    @Test
    void checkUrlWorksWhenMalformedUrlSent() throws IOException {
        assertThrows(MalformedURLException.class, () -> CardServiceImpl.checkUrlWorks("hello"));
    }


}