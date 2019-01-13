package com.pujan.androidchatapp.websocket;

public interface WebSocketPresenter {

    void startWebSocket();

    void disconnectWebSocket();

    void sendMessage(String message);


}
