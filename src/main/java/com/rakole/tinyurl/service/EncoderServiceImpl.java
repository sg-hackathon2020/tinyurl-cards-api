package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.EncoderService;
import com.rakole.tinyurl.enums.EncoderType;
import com.rakole.tinyurl.enums.MessageDigestType;
import com.rakole.tinyurl.factories.EncoderFactory;
import com.rakole.tinyurl.factories.MessageDigestFactory;
import com.rakole.tinyurl.model.CustomEncoderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Service
public class EncoderServiceImpl implements EncoderService {
    private final CustomEncoderProperties customEncoderProperties;

    @Autowired
    public EncoderServiceImpl(CustomEncoderProperties customEncoderProperties) {
        this.customEncoderProperties = customEncoderProperties;
    }

    @Override
    public String transform(EncoderType encoderType, MessageDigestType messageDigestType, String url) throws NoSuchAlgorithmException {
        if (url == null)
            throw new IllegalArgumentException();
        Base64.Encoder encoder = EncoderFactory.getEncoder(encoderType);
        MessageDigest messageDigest = MessageDigestFactory.getMessageDigest(messageDigestType);
        byte[] transformedArray = encoder.encode(messageDigest.digest(url.getBytes()));
        return new String(transformedArray);
    }


    @Override
    public String shortenUrl(EncoderType encoderType, MessageDigestType messageDigestType, String url) throws NoSuchAlgorithmException {
        String transFormedString = transform(encoderType, messageDigestType, url);
        StringBuilder sb = new StringBuilder();
        int i = customEncoderProperties.getLength() != 0 ? customEncoderProperties.getLength() : 6;
        Random random = new Random();
        char[] transformedArray = transFormedString.toCharArray();
        for (i = 0; i < customEncoderProperties.getLength(); i++) {
            sb.append(transformedArray[random.nextInt(22)]);
        }
        return sb.toString();
    }

}
