package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class TimeUnitNotFoudException extends RuntimeException{
    public TimeUnitNotFoudException(long id){
        super(MessageFormat.format("Could not find time unit id {0}", id));
    }
}
