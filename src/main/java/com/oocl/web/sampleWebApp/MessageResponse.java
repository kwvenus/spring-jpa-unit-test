package com.oocl.web.sampleWebApp;

import javax.persistence.*;

public class MessageResponse {
    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(){

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}