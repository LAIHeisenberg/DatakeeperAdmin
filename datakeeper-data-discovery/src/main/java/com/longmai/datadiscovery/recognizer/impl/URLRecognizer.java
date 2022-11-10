package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class URLRecognizer extends AbstractRecognizer {

    public URLRecognizer(String name){
        super(name);
    }

    @Override
    protected String getRegex() {
        return "(https?|ftp|file):\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    }

    public static void main(String[] args){

        String text ="qwoerujasdoij\nasdfoiurehttp://zh.wikipedia.org/wiki/%E5%BE%80%E6%9D%A5%E6%B8%AF%E6%BE%B3%E9%80%9A%E8%A1%8C%E8%AF%81达普";
        URLRecognizer urlRecognizer = new URLRecognizer(null);
        System.out.println(urlRecognizer.recognize(text));

    }

}
