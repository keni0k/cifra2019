package com.example.heroku.utils;

public class MessageUtil {
    public MessageUtil(String type, String message){
       this.type = type;
       this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    private String type;
    private String message;
}
