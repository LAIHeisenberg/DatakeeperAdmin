package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class IMEIRecognizer extends AbstractRecognizer {

    public IMEIRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "([0-9]{15})|([0-9]{6}-[0-9]{2}-[0-9]{6}-[0-9])";
    }
}
