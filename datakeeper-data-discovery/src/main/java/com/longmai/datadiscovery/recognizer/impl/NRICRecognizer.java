package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

/**
 * 新加坡身份份
 *
 * 国民身份证号由9位组成。其中首位为字母"S"，"T"，"F"或"G"，第2至8位为数字，末位为字母校验码。
 */
public class NRICRecognizer extends AbstractRecognizer {

    public NRICRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "[SFTG]\\d{8}";
    }

    public  static void main(String[] args){
        String text = "asdfqwerF58954125ad";
        NRICRecognizer nricRecognizer = new NRICRecognizer(null);
        System.out.println(nricRecognizer.recognize(text));

    }
}
