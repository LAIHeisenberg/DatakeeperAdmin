package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class EmailRecognizer extends AbstractRecognizer {

    @Override
    protected String getRegex() {
        return "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)";
    }

    public EmailRecognizer(String name){
        super(name);
    }

    public static void main(String[] args){
        String text = "asfqdaiod\nweoadfu49324g@rkjwerisdf#erxp的俄文32\n 394343@qodi.comq到iu其巍峨他";
        EmailRecognizer emailRecognizer = new EmailRecognizer(null);
        System.out.println(emailRecognizer.recognize(text));

    }
}
