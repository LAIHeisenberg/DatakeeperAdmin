package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class MilitaryIDRecognizer extends AbstractRecognizer {

    public MilitaryIDRecognizer(String name) {
        super(name);
    }

    /**
     * 2016式《中国人民解放军军官证》主要具有以下特点：一是编制唯一证件号，统一采取“军”冠字头加7位数字的形式编码，
     * 人员从进入干部队伍到退出，不管职务岗位如何调整变换，号码始终不变，增强了证件的唯一性、权威性及法律效应
     */

    @Override
    protected String getRegex() {
        return "军\\d{7}";
    }

}
