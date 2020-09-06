package com.rakole.tinyurl.util;

import com.rakole.tinyurl.enums.EncoderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncoderFactoryTest {

    @Test
    @DisplayName("when fetching EncoderType.ENCODER")
    void getEncoderTypeEncoder() {
        Base64.Encoder encoder = Base64.getEncoder();
        assertEquals(encoder.getClass(), EncoderFactory.getEncoder(EncoderType.ENCODER).getClass());
    }

    @Test
    @DisplayName("when fetching EncoderType.ENCODER")
    void getEncoderTypeUrlEncoder() {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        assertEquals(encoder.getClass(), EncoderFactory.getEncoder(EncoderType.URL_ENCODER).getClass());
    }
}