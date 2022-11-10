package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class Sha512Recognizer extends AbstractRecognizer {

    public Sha512Recognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-9a-f]{40}|[0-9A-F]{128}";
    }

    //89°00'00.0"S 180°00'00.0"E
    //-89.000000, 180.000000
}
