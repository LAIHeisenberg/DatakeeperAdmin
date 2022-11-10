package com.longmai.datadiscovery.recognizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class RecognizerFactory {

    public static List<RecognizerResult> recognizer(String text, RecognizerGroup recognizerGroup){
        final List<RecognizerResult> recognizerResults = new ArrayList<>();
        recognizerGroup.getRecognizers().forEach(new Consumer<AbstractRecognizer>() {
            @Override
            public void accept(AbstractRecognizer recognizer) {
                recognizer.recognize(text).forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        recognizerResults.add(new RecognizerResult(s, recognizer.getName()));
                    }
                });
            }
        });
        return recognizerResults.size() > 0 ? recognizerResults : Collections.emptyList();
    }

}
