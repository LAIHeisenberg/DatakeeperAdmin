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

}
