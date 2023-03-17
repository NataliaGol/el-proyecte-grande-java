package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class ProvinceNotFoudException extends RuntimeException{
    public ProvinceNotFoudException(long id){
        super(MessageFormat.format("Could not find province id {0}", id));
    }
}
