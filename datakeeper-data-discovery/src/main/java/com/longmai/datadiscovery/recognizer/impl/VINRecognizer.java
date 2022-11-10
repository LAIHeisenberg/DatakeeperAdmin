package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

/**
 * 车辆识别代码
 */
public class VINRecognizer extends AbstractRecognizer {

    public VINRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[A-HJ-NPR-Z\\d]{17}";
    }
}
