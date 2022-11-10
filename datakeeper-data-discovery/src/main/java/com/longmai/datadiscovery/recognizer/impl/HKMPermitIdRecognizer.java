package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;


public class HKMPermitIdRecognizer extends AbstractRecognizer {

    public HKMPermitIdRecognizer(String name) {
        super(name);
    }

    /**
     * 通行证号码组成规则：通行证证件号码共11位。第1位为字母，“H”字头签发给香港居民，“M”字头签发给澳门居民；
     * 第2位至第11位为数字，前8位数字为通行证持有人的终身号，后2位数字表示换证次数，首次发证为00，此后依次递增
     */
    @Override
    protected String getRegex() {
        return "(H|M)\\d{10}";
    }

}
