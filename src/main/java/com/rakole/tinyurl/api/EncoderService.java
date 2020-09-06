package com.rakole.tinyurl.api;

import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;

import java.security.NoSuchAlgorithmException;

public interface EncoderService {
    String transform(EncoderType encoderType, MessageDigestType messageDigestType, String url) throws NoSuchAlgorithmException;
}
