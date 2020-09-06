package com.rakole.tinyurl.factories;

import com.rakole.tinyurl.enums.MessageDigestType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestFactory {
    public static MessageDigest getMessageDigest(MessageDigestType messageDigestType) throws NoSuchAlgorithmException {
        if (messageDigestType == null || !messageDigestType.equals(MessageDigestType.MD5))
            throw new NoSuchAlgorithmException();
        else
            return MessageDigest.getInstance("md5");
    }
}
