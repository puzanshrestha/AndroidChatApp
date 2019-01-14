package com.pujan.androidchatapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pujan.androidchatapp.model.SignInPresenterImpl;
import com.pujan.androidchatapp.presenter.SignInPresenter;
import com.pujan.androidchatapp.view.SignInView;
import com.pujan.androidchatapp.websocket.MessageModel;
import com.pujan.androidchatapp.websocket.WebSocketImpl;
import com.pujan.androidchatapp.websocket.WebSocketPresenter;
import com.pujan.androidchatapp.websocket.WebSocketView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SignInView, WebSocketView {

    SignInPresenter signInPresenter;
    WebSocketPresenter webSocketPresenter;

    Button button;
    TextView textView;
    EditText messageBox;
    RecyclerView recyclerView;

    ArrayList<MessageModel> messageLists;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInPresenter = new SignInPresenterImpl(MainActivity.this);
        signInPresenter.signIn("test", "test");

        webSocketPresenter = new WebSocketImpl(MainActivity.this);
        webSocketPresenter.startWebSocket();

        button = (Button) findViewById(R.id.send_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        messageBox = (EditText) findViewById(R.id.message_box);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        messageLists = new ArrayList<>();

        mAdapter = new MyAdapter(messageLists);
        recyclerView.setAdapter(mAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webSocketPresenter.sendMessage(messageBox.getText().toString().trim());
                messageBox.setText("");
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final MessageModel messageObject = new Gson().fromJson(message, MessageModel.class);
                messageLists.add(messageObject);
                mAdapter.notifyDataSetChanged();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                    }
                });

            }
        });


    }

    @Override
    public void disconnected() {

    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        ArrayList<MessageModel> chatLists;

        public MyAdapter(ArrayList<MessageModel> chatLists) {
            this.chatLists = chatLists;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.message.setText(chatLists.get(position).getMessage());

        }

        @Override
        public int getItemCount() {
            return chatLists.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView message;

            public MyViewHolder(View itemView) {
                super(itemView);
                message = (TextView) itemView.findViewById(R.id.message);

            }
        }
    }
}
