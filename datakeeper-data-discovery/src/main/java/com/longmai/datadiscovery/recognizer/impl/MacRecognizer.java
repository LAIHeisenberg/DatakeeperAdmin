package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class MacRecognizer extends AbstractRecognizer {

    public MacRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "(([0-9a-fA-F]){2}:){5}[0-9a-fA-F]{2}";
    }

    public static void main(String[] args){
        String mac = "ad:00:0A:02:0a:03:0Cetwetqer";
        MacRecognizer macRecognizer = new MacRecognizer(null);
        System.out.println(macRecognizer.recognize(mac));
    }

}
