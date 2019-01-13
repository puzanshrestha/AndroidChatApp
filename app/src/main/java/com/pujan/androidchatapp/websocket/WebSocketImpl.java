package com.pujan.androidchatapp.websocket;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;


public class WebSocketImpl implements WebSocketPresenter {

    WebSocketView webSocketView;
    WebSocket ws;

    public WebSocketImpl(WebSocketView webSocketView) {
        this.webSocketView = webSocketView;
    }

    @Override
    public void startWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("wss://pujan-python.herokuapp.com/main/ws/chatroom/aaa/").build();
        EchoSocketListener listener = new EchoSocketListener(webSocketView);
        ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }


    @Override
    public void disconnectWebSocket() {

    }

    @Override
    public void sendMessage(String messages) {

        String json = "{\"actionType\" : \"message\"," +
                "\"sender\": \"android\"," +
                "\"message\": \"" + messages + "\"," +
                "\"chatRoom\": \"aaa\"}";
        ws.send(json);
        System.out.println(json);
    }


}
