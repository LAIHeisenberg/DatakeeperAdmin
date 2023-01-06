package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class PemRecognizer extends AbstractRecognizer {

    String regex = "-----BEGIN .*-----\\n([a-zA-Z0-9+/=]+\\n)+-----END .*-----";

    public PemRecognizer(String name){
        super(name);
    }

    @Override
    protected String getRegex() {
        return regex;
    }

    public static void main(String[] args){

        String pem = "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
                "-----END OPENSSH PRIVATE KEY-----";

        PemRecognizer pemRecognizer = new PemRecognizer(null);
        System.out.println(pemRecognizer.recognize(pem));
        System.out.println(pemRecognizer.getRegex());

    }

}
