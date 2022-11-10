package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class PassportIdRecognizer extends AbstractRecognizer {

    public PassportIdRecognizer(String name) {
        super(name);
    }


    /**
     * 护照号由一位字母和八位数字组成，在护照第一页右上角。根据英语单词首字母，不同类型的护照有不同的护照号格式，
     * 其中有电子芯片的普通护照为“E”字开头，后接8位阿拉伯数字，外交护照以“D”开头，公务护照以“S”开头，公务普通护照以“P”开头，
     * 旧版无电子芯片的普通护照以“G”开头。
     *
     */
    @Override
    protected String getRegex() {
        return "[EGDSP][0-9]{8}";
    }
}
