package com.apress.prospring5.ch4;

import java.security.MessageDigest;

public class MessageDigester {
    private MessageDigest digest1;
    private MessageDigest digest2;

    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }

    public void digest(String message) {
        System.out.println("Using digest1");
        digest(message, digest1);

        System.out.println("Using digest2");
        digest(message, digest2);
    }

    private void digest(String message, MessageDigest digest) {
        System.out.println("Using algorithm: " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = message.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println(out);
    }
}
