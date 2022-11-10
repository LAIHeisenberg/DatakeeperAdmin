package com.longmai.datadiscovery.recognizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRecognizer {

   public List<String> recognize(String text){
       if (Objects.isNull(text) || "".equals(text.trim())){
           return Collections.emptyList();
       }
       String regx = getRegex();
       Pattern compile = Pattern.compile(regx,Pattern.UNICODE_CHARACTER_CLASS | Pattern.MULTILINE | Pattern.UNIX_LINES);
       Matcher matcher = compile.matcher(text);
       List<String> result = new ArrayList<>();
       while (matcher.find()){
           result.add(matcher.group());
       }
       return result;
   }

   public AbstractRecognizer(String name){
       this.name = name;
   }

   protected String name;

   protected abstract String getRegex();

   public String getName(){
       return this.name == null ? this.getClass().getSimpleName() : this.name;
   }

}
