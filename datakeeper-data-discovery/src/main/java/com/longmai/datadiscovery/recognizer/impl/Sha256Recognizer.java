package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class Sha256Recognizer extends AbstractRecognizer {

    public Sha256Recognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-9a-f]{40}|[0-9A-F]{64}";
    }
}
