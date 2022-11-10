package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class Ipv4Recognizer extends AbstractRecognizer {

    public Ipv4Recognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "((2(5[0-5]|[0-4][0-9])|1[0-9]{2}|([1-9][0-9])(?![0-9])|[0-9](?![0-9]))\\.?){4}";
    }

    public static void main(String[] args){
        String text = "129.122.141.21";
        Ipv4Recognizer ipv4Recognizer = new Ipv4Recognizer(null);
        System.out.println(ipv4Recognizer.recognize(text));
    }

}
