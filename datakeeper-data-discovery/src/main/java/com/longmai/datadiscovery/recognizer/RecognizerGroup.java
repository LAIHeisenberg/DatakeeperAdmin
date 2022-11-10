package com.longmai.datadiscovery.recognizer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum RecognizerGroup {

    ALL("全部"),
    GROUP_1("分组1", RecognizerEnums.DATE, RecognizerEnums.EMAIL),

    ;
    RecognizerGroup(String desc, RecognizerEnums ... recognizers){
        if (!"ALL".equals(this.name()) && recognizers.length == 0){
            throw new IllegalArgumentException("未指定recognizers");
        }
        this.desc = desc;
        this.recognizers = Arrays.asList(recognizers);
    }

    public List<AbstractRecognizer> getRecognizers(){
        if (this == RecognizerGroup.ALL){
            this.recognizers = Arrays.asList(RecognizerEnums.values());
        }
        return this.recognizers.stream().map(new Function<RecognizerEnums, AbstractRecognizer>() {
            @Override
            public AbstractRecognizer apply(RecognizerEnums recognizerEnum) {
                return recognizerEnum.getRecognizer();
            }
        }).collect(Collectors.toList());
    }

    private List<RecognizerEnums> recognizers;
    private String desc;

}
