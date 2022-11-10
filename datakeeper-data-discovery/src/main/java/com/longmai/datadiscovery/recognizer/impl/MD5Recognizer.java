package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class MD5Recognizer extends AbstractRecognizer {

    public MD5Recognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-9a-f]{32}|[0-9A-F]{32}";
    }

    public static void main(String[] args){

        String inputText = "32e76218b08a084b7e234878f0101313";
        MD5Recognizer md5Recognizer = new MD5Recognizer(null);
        System.out.println(md5Recognizer.recognize(inputText));
    }

}
