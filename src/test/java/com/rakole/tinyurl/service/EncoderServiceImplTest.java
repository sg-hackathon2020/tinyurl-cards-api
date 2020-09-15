package com.rakole.tinyurl.service;

import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import com.rakole.tinyurl.model.CustomEncoderProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EncoderServiceImplTest {
    @Mock
    private CustomEncoderProperties customEncoderProperties;

    @InjectMocks
    private EncoderServiceImpl encoderService;


    @Test
    @DisplayName("When transform is called with null value, the method should throw IllegalArgumentException")
    void transformWithNullString() {
        assertThrows(IllegalArgumentException.class, () -> encoderService.transform(EncoderType.ENCODER, MessageDigestType.MD5, null));
    }

    @Test
    @DisplayName("When transforming URL using md5 hash and base encoder")
    void transformWithEncoderAndMd5Hash() throws NoSuchAlgorithmException {
        assertEquals("HVkg9LRLJ6gCvXfE8FNvWg==", encoderService.transform(EncoderType.ENCODER, MessageDigestType.MD5, "google.com"));
    }

    @Test
    @DisplayName("When transforming URL using md5 hash and url encoder")
    void transformWithUrlEncoderAndMd5Hash() throws NoSuchAlgorithmException {
        assertEquals("HVkg9LRLJ6gCvXfE8FNvWg==", encoderService.transform(EncoderType.URL_ENCODER, MessageDigestType.MD5, "google.com"));
    }

    @Test
    @DisplayName("When we call shortenUrl method")
    void test() throws NoSuchAlgorithmException {
        given(customEncoderProperties.getLength()).willReturn(6);
        String str = encoderService.shortenUrl(EncoderType.URL_ENCODER, MessageDigestType.MD5, "google.com");
        assertNotNull(str);
        assertEquals(6, str.length());
    }

}