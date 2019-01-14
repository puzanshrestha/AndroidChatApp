package com.pujan.androidchatapp.websocket;

public class MessageModel {

    String actionType;
    String message;
    String sender;
    String chatRoom;

    public MessageModel(String actionType, String message, String sender, String chatRoom) {
        this.actionType = actionType;
        this.message = message;
        this.sender = sender;
        this.chatRoom = chatRoom;
    }

    public MessageModel() {

    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }
}
