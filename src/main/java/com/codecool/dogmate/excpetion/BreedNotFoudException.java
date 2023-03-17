package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class BreedNotFoudException extends RuntimeException{
    public BreedNotFoudException(long id){
        super(MessageFormat.format("Could not find breed id {0}", id));
    }
}
