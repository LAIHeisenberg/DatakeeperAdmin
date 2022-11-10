package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

/**
 * 社会保障号码是由九个数字组成的号码，格式为“AAA-GG-SSSS”。
 * 号码分为三个部分： 前三位称作区域号码，因它们曾经按照地理区域进行分配；中间两位称作群组号码；最后四位为序号。
 */
public class SSNRecognizer extends AbstractRecognizer {

    public SSNRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-7][0-9]{2}-[0-9]{2}-[0-9]{4}";
    }

    public static void main(String[] args){
        String text = "wera056-85-8564";
        SSNRecognizer ssnRecognizer = new SSNRecognizer(null);
        System.out.println(ssnRecognizer.recognize(text));
    }
}
