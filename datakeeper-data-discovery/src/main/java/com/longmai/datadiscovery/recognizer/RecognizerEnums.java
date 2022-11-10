package com.longmai.datadiscovery.recognizer;

import com.longmai.datadiscovery.recognizer.impl.*;

public enum RecognizerEnums {

    CREDIT_CARD(new CreditCardRecognizer("信用卡号")),
    DATE(new DateRecognizer("日期")),
    EMAIL(new EmailRecognizer("邮箱地址")),
    ID_NUMBER(new IdNumberRecognizer("身份证号码")),
    PEM(new PemRecognizer("PEM文件")),
    PHONE_NUMBER(new PhoneNoRecognizer("手机号")),
    URL(new URLRecognizer("URL"))
    ;

    private AbstractRecognizer recognizer;

    RecognizerEnums(AbstractRecognizer recognizer){
        this.recognizer = recognizer;

    }

    public AbstractRecognizer getRecognizer(){
        return this.recognizer;
    }

}
