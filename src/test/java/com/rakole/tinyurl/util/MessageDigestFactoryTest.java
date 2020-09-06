package com.rakole.tinyurl.util;

import com.rakole.tinyurl.enums.MessageDigestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageDigestFactoryTest {

    @Test
    @DisplayName("When requesting MD5 type of message digest")
    void getMessageDigest() {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            assertEquals(md.getClass(), MessageDigestFactory.
                    getMessageDigest(MessageDigestType.MD5).getClass());
            assertEquals(md.getAlgorithm(), MessageDigestFactory.
                    getMessageDigest(MessageDigestType.MD5).getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
}