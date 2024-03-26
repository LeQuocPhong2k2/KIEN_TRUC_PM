package com.example.springmq.service;

import java.util.Base64;

public class BashService {
    public String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public String decode(String text) {
        byte[] decodeBytes = Base64.getDecoder().decode(text);
        return new String(decodeBytes);
    }
}
