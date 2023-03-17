package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class UserTypeNotFoudException extends RuntimeException{
    public UserTypeNotFoudException(long id){
        super(MessageFormat.format("Could not find user type id {0}", id));
    }
}
