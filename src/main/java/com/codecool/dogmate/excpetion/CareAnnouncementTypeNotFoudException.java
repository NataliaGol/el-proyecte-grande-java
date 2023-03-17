package com.codecool.dogmate.excpetion;

import java.text.MessageFormat;

public class CareAnnouncementTypeNotFoudException extends RuntimeException{
    public CareAnnouncementTypeNotFoudException(long id){
        super(MessageFormat.format("Could not find Care Announcement Type id {0}", id));
    }
}
