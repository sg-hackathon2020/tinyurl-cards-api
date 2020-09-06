package com.rakole.tinyurl.factories;

import com.rakole.tinyurl.enums.EncoderType;

import java.util.Base64;

public class EncoderFactory {
    public static Base64.Encoder getEncoder(EncoderType encoderType) {
        switch (encoderType) {

            case URL_ENCODER:
                return Base64.getUrlEncoder();
            case ENCODER:
            default:
                return Base64.getEncoder();
        }
    }
}
