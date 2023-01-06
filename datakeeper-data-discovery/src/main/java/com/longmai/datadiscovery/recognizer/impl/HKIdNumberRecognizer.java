package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class HKIdNumberRecognizer extends AbstractRecognizer {

    public HKIdNumberRecognizer(String name) {
        super(name);
    }

    /**
     * C668668(9) 香港身份证号码由三部分组成：一个英文字母;6个数字;括号及0-9中的任一个数字，或者字母A。
     * 括号中的数字或字母A，是校验码，用于检验括号前面的号码的逻辑正确性。
     */
    @Override
    protected String getRegex() {
        return "[A-Z][0-9]{6}\\([0-9A]\\)";
    }

    public static void main(String[] args){
        System.out.println(new HKIdNumberRecognizer("").getRegex());
    }
}
