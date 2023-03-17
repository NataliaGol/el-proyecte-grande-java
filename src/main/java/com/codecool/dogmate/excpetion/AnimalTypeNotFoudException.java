package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class AnimalTypeNotFoudException extends RuntimeException{
    public AnimalTypeNotFoudException(long id){
        super(MessageFormat.format("Could not find animal type id {0}", id));
    }
}
