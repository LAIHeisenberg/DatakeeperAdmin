package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class TelephoneRecognizer extends AbstractRecognizer {

    public TelephoneRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
    }

}
