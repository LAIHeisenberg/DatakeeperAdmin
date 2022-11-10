package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

/**
 * 日期识别器
 */
public class DateRecognizer extends AbstractRecognizer {

    // 2022-11-04 | 2022/11/04 | 2022.11.04
    private String reg_1 = "(([1|2|3]\\d{3})([-/.])(0[1-9]|10|11|12)\\3([0-2][1-9]|30|31))";
    // 2022年11月04日
    private String reg_2 = "(([1|2|3]\\d{3})年(0[1-9]|10|11|12)月([0-2][1-9]|30|31)日)";
    //04/11/2022
    private String reg_3 = "([0-2][1-9]|30|31)[-/.](0[1-9]|10|11|12)[-/.]([1|2|3]\\d{3})";
    private String reg_4 = "(([0-2][1-9]|30|31)([-/.])(0[1-9]|10|11|12)\\15([0-9][0-9]))";

    public DateRecognizer(String name){
        super(name);
    }

    @Override
    public String getRegex() {
        return reg_1.concat("|").concat(reg_2).concat("|").concat(reg_3).concat("|").concat(reg_4);
    }


    public static void main(String[] args){
        String text = "asdfqwer道派阿斯地方asdf2022年11月04日qwe2022-12-05日asdf04/11/20qwer";
        DateRecognizer rec = new DateRecognizer(null);
        System.out.println(rec.recognize(text));
    }
}
