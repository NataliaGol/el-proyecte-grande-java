package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class VoivodeshipNotFoudException extends RuntimeException{
    public VoivodeshipNotFoudException(long id){
        super(MessageFormat.format("Could not find voivodeship id {0}", id));
    }
}
