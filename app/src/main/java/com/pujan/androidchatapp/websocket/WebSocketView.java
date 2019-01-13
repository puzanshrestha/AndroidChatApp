package com.pujan.androidchatapp.websocket;

public interface WebSocketView {
    public void connected();

    public void sendMessage(String message);

    public void receiveMessage(String message);

    public void disconnected();

}
