package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class PhoneNoRecognizer extends AbstractRecognizer {
    // 11位数字
    // 中国移动 134/135/136/137/138/139/147/148/150/151/152/157/158/159/172/178/182/183/184/187/188/195/197/198/165/1709
    // 中国联通 130/131/132/145/146/155/156/166/175/176/185/186/196/167/171/1700
    // 中国电信 133/149/153/173/177/180/181/189/190/191/193/199/162/1705
    // 中国广电 192

    public PhoneNoRecognizer(String name){
        super(name);
    }

    @Override
    protected String getRegex() {
        return "(((134)|(135)|(136)|(137)|(138)|(139)|(147)|(148)|(150)|(151)|(152)|(157)|(158)" +
                "|(159)|(172)|(178)|(182)|(183)|(184)|(187)|(188)|(195)|(197)|(198)|(165)|(130)|(131)" +
                "|(132)|(145)|(146)|(155)|(156)|(166)|(175)|(176)|(185)|(186)|(196)|(167)|(171)|(133)" +
                "|(149)|(153)|(173)|(177)|(180)|(181)|(189)|(190)|(191)|(193)|(199)|(162)|(192))[0-9]{8})" +
                "|(((1709)|(1700)|(1705))[0-9]{7})";
    }

    public static void main(String[] args){
        PhoneNoRecognizer phoneNoRecognizer = new PhoneNoRecognizer(null);
        String text = "asdfda阿大夫完全哦噢如dadfasjdf183 14010018985 asdfqwer54654asdfaweragsdfg18310018698asdfwer";
        System.out.println(phoneNoRecognizer.recognize(text));
    }
}
