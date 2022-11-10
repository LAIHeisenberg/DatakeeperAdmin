package com.longmai.datadiscovery.recognizer;

public class RecognizerResult {

    private String target;
    private String desc;

    public RecognizerResult(String target, String desc){
        this.target = target;
        this.desc = desc;
    }

    public String getTarget(){
        return this.target;
    }

    public String getDesc(){
        return this.desc;
    }

}
