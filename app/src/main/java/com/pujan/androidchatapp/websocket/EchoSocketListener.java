package com.pujan.androidchatapp.websocket;


import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

public class EchoSocketListener extends okhttp3.WebSocketListener {
    private WebSocketView webSocketView;

    public EchoSocketListener(WebSocketView webSocketView) {
        this.webSocketView = webSocketView;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        System.out.println(response.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        webSocketView.receiveMessage(text.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        System.out.println(reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }
}