package com.example.foodhub.Customer;
import android.text.TextWatcher;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodhub.Chat.MessageAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.Const;
import com.example.foodhub.Common.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class OrderChatFragment extends Fragment implements TextWatcher {

    private final long orderId;
    private final String username;
    private final String password;
    private final String user;
    private final String toWhom;

    private View page;
    private WebSocket webSocket;
    // private String CHAT_SERVER_PATH = "ws://SERVER-IP-HERE:PORT-NUMBER-HERE"; 192.168.0.136
    private EditText messageEdit;
    private View sendBtn, pickImgBtn;
    private RecyclerView recyclerView;
    private int IMAGE_REQUEST_ID = 1;
    private MessageAdapter messageAdapter;

    public OrderChatFragment(long orderId, String username, String password, String user, String toWhom) {
        this.orderId = orderId;
        this.username = username;
        this.password = password;
        this.user = user;
        this.toWhom = toWhom;
    }

    public OrderChatFragment() {
        this.orderId = 0;
        this.username = null;
        this.password = null;
        this.user = null;
        this.toWhom = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_order_chat, container, false);
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        JSONObject obj = new JSONObject(map);
        try{obj.put("id",orderId);
            Call.post(user+"s-get-otmessages", obj, this::initiateSocketConnection, null);
        } catch (JSONException e) {e.printStackTrace();}
        return page;
    }

    public void getChatHistory() {
    }

    public void initiateSocketConnection(JSONArray arr) {
        OkHttpClient client = new OkHttpClient();
        String link;
        if (user.equals("customer")) {
            link = Const.CHAT_URL + "OTC/"+String.valueOf(orderId);
            // Toast.makeText(getActivity().getApplicationContext(), "customer", Toast.LENGTH_SHORT).show();
        }
        else {
            link = Const.CHAT_URL + "OTF/"+String.valueOf(orderId);
            // Toast.makeText(getActivity().getApplicationContext(), "firm", Toast.LENGTH_SHORT).show();
        }
        Request request = new Request.Builder().url(link).build();
        webSocket = client.newWebSocket(request, new SocketListener());
        Message[] messages = new Message[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            try{Message message = new Message(arr.getJSONObject(i));
                messages[message.getSequence()-1] = message;
            } catch (JSONException e) {e.printStackTrace();}
        }
        int match = user.equals("customer") ? 1 : 0;
        for (Message message : messages) {
            if (message.getWho() == match) {
                JSONObject jsonObject = new JSONObject();
                try{jsonObject.put("name", username);
                    jsonObject.put("message", message.getMessage());
                    jsonObject.put("isSent", true);
                    messageAdapter.addItem(jsonObject);
                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    resetMessageEdit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                getActivity().runOnUiThread(() -> {
                    JSONObject jsonObject = new JSONObject();
                    try{jsonObject.put("name", username);
                        jsonObject.put("message", message.getMessage());
                        jsonObject.put("isSent", false);
                        messageAdapter.addItem(jsonObject);
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override public void afterTextChanged(Editable s) {
        String string = s.toString().trim();
        if (string.isEmpty()) {
            resetMessageEdit();
        } else {
            sendBtn.setVisibility(View.VISIBLE);
            pickImgBtn.setVisibility(View.INVISIBLE);
        }
    }

    private void resetMessageEdit() {
        messageEdit.removeTextChangedListener(this);
        messageEdit.setText("");
        sendBtn.setVisibility(View.INVISIBLE);
        pickImgBtn.setVisibility(View.VISIBLE);
        messageEdit.addTextChangedListener(this);
    }

    private class SocketListener extends WebSocketListener {
        @Override public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            getActivity().runOnUiThread(() -> {
//                Toast.makeText(OrderChatFragment.class,
//                        "Socket Connection Successful!",
//                        Toast.LENGTH_SHORT).show();
                initializeView();
            });
        }
        @Override public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            getActivity().runOnUiThread(() -> {
                try{JSONObject jsonObject = new JSONObject(text);
                    jsonObject.put("isSent", false);
                    messageAdapter.addItem(jsonObject);
                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void initializeView() {
        messageEdit = page.findViewById(R.id.messageEdit);
        sendBtn = page.findViewById(R.id.sendBtn);
        pickImgBtn = page.findViewById(R.id.pickImgBtn);
        recyclerView = page.findViewById(R.id.recyclerView);
        // sendBtn.setVisibility(View.VISIBLE);
        messageAdapter = new MessageAdapter(getLayoutInflater());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        messageEdit.addTextChangedListener(this);
        sendBtn.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", username);
                jsonObject.put("message", messageEdit.getText().toString());
                webSocket.send(jsonObject.toString());
                jsonObject.put("isSent", true);
                messageAdapter.addItem(jsonObject);
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                resetMessageEdit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        pickImgBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Pick image"),
                    IMAGE_REQUEST_ID);
        });
    }

    @Override public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_ID && resultCode == -1) {
            try {
                InputStream is = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);
                sendImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendImage(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
        String base64String = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", username);
            jsonObject.put("image", base64String);
            webSocket.send(jsonObject.toString());
            jsonObject.put("isSent", true);
            messageAdapter.addItem(jsonObject);
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
