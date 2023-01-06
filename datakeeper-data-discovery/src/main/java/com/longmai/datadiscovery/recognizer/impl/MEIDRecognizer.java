package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class MEIDRecognizer extends AbstractRecognizer {

    public MEIDRecognizer(String name){
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-9A-Fa-f]{14}";
    }

}
