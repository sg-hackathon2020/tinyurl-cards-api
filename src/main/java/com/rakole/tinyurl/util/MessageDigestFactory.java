package com.rakole.tinyurl.util;

import com.rakole.tinyurl.enums.MessageDigestType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestFactory {
    public static MessageDigest getMessageDigest(MessageDigestType messageDigestType) throws NoSuchAlgorithmException {
        //add a switch statement here in the future when supporting multiple algorithms
        return MessageDigest.getInstance("md5");
    }
}
