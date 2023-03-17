package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class LessonNotFoudException extends RuntimeException{
    public LessonNotFoudException(long id){
        super(MessageFormat.format("Could not find lesson id {0}", id));
    }
}
