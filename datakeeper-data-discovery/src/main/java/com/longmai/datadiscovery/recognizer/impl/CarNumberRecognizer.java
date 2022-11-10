package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class CarNumberRecognizer extends AbstractRecognizer {


    public CarNumberRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})";
    }

    public static void main(String[] args){

        String text = "as京8879d桂L00004fqwer京NA988,京ES68702";
        CarNumberRecognizer carNumberRecognizer = new CarNumberRecognizer(null);
        System.out.println(carNumberRecognizer.recognize(text));
    }






}
