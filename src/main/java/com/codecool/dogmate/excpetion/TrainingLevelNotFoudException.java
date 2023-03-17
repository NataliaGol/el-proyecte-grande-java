package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class TrainingLevelNotFoudException extends RuntimeException{
    public TrainingLevelNotFoudException(long id){
        super(MessageFormat.format("Could not find training level id {0}", id));
    }
}
