package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

/**
 * 组织机构代码
 */
public class OrganizationCodeRecognizer extends AbstractRecognizer {

    public OrganizationCodeRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[0-9A-Z]{8}[—-]?[0-9A-Z]";
    }

}
