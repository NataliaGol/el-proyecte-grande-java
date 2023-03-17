package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class CityNotFoudException extends RuntimeException{
    public CityNotFoudException(long id){
        super(MessageFormat.format("Could not find city id {0}", id));
    }
}
