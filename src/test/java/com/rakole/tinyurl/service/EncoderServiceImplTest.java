package com.rakole.tinyurl.service;

import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncoderServiceImplTest {

    @InjectMocks
    EncoderServiceImpl encoderService;

    @Test
    @DisplayName("When transform is called with null value, the method should throw IllegalArgumentException")
    void transformWithNullString() {
        assertThrows(IllegalArgumentException.class, () -> encoderService.transform(EncoderType.ENCODER, MessageDigestType.MD5, null));
    }

    @Test
    @DisplayName("When transforming URL using md5 hash and base encoder")
    void transformWithEncoderAndMd5Hash() throws NoSuchAlgorithmException {
        assertNotNull(encoderService.transform(EncoderType.ENCODER, MessageDigestType.MD5, "google.com"));
        assertEquals("850aef63040767c64f4c90547ba26a27", encoderService.transform(EncoderType.ENCODER, MessageDigestType.MD5, "google.com"));
    }

    @Test
    @DisplayName("When transforming URL using md5 hash and url encoder")
    void transformWithUrlEncoderAndMd5Hash() throws NoSuchAlgorithmException {
        assertNotNull(encoderService.transform(EncoderType.URL_ENCODER, MessageDigestType.MD5, "google.com"));
        assertEquals("850aef63040767c64f4c90547ba26a27", encoderService.transform(EncoderType.URL_ENCODER, MessageDigestType.MD5, "google.com"));
    }

}