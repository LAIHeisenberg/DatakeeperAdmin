package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class GenderRecognizer extends AbstractRecognizer {

    public GenderRecognizer(String name) {
        super(name);
    }
    @Override
    protected String getRegex() {
        return "男|女|男性|女性|偏男两性人|偏女两性人|双性人|不完全男性|不完全女性|male|female|intersex";
    }

    public static void main(String[] args){
        String text = "a大否dapij554asdf a男人dapoiwer不完全男性asdfdaintersexqwerasdmale";
        GenderRecognizer genderRecognizer = new GenderRecognizer(null);
        System.out.println(genderRecognizer.recognize(text));
    }

}
