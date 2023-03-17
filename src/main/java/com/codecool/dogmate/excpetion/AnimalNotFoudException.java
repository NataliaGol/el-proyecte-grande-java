package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class AnimalNotFoudException extends RuntimeException{
    public AnimalNotFoudException(long id){
        super(MessageFormat.format("Could not find animal id {0}", id));
    }
}
