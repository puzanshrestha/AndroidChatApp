package com.pujan.androidchatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pujan.androidchatapp.model.SignInPresenterImpl;
import com.pujan.androidchatapp.presenter.SignInPresenter;
import com.pujan.androidchatapp.view.SignInView;
import com.pujan.androidchatapp.websocket.WebSocketImpl;
import com.pujan.androidchatapp.websocket.WebSocketPresenter;
import com.pujan.androidchatapp.websocket.WebSocketView;

public class MainActivity extends AppCompatActivity implements SignInView, WebSocketView {

    SignInPresenter signInPresenter;
    WebSocketPresenter webSocketPresenter;

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInPresenter = new SignInPresenterImpl(MainActivity.this);
        signInPresenter.signIn("test", "test");

        webSocketPresenter = new WebSocketImpl(MainActivity.this);
        webSocketPresenter.startWebSocket();

        button = (Button) findViewById(R.id.send_button);
        textView = (TextView) findViewById(R.id.text_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webSocketPresenter.sendMessage("Hello from android web socket");
            }
        });

    }

    @Override
    public void showValidationError() {
        Toast.makeText(this, "validationError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(this, "Sign In success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInError() {
        Toast.makeText(this, "Sign in error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void connected() {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void receiveMessage(final String message) {
        //websocket response from the server is handled here in main ui thread
        System.out.println(message);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(message);
            }
        });


    }

    @Override
    public void disconnected() {

    }
}
