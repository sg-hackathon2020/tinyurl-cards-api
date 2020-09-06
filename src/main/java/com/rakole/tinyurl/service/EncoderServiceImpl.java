package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.EncoderService;
import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import com.rakole.tinyurl.util.EncoderFactory;
import com.rakole.tinyurl.util.MessageDigestFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncoderServiceImpl implements EncoderService {
    @Override
    public String transform(EncoderType encoderType, MessageDigestType messageDigestType, String url) throws NoSuchAlgorithmException {
        if (url == null)
            throw new IllegalArgumentException();
        Base64.Encoder encoder = EncoderFactory.getEncoder(encoderType);
        MessageDigest messageDigest = MessageDigestFactory.getMessageDigest(messageDigestType);
        BigInteger bigInteger = new BigInteger(1, messageDigest.digest(encoder.encode(url.getBytes())));
        return bigInteger.toString(16);
    }
}
