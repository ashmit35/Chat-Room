package com.chatroom.Models;

public class MessagesModel {
    private String uId,message;
    private Long timeStamp;

    public MessagesModel(String uId, String message, Long timeStamp) {
        this.uId = uId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getuId() {
        return uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MessagesModel(String uId, String message){
        this.uId = uId;
        this.message = message;
    }

    public MessagesModel(){}

}
