package com.example.android_broadcastreceiver.BroadcastReceiver_Object;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_broadcastreceiver.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class BroadcastReceiver_Object_MainActivity extends AppCompatActivity {
    private  static  final  String MY_ACTION = "com.tinCoder.ACTION";
    private static final String MY_KEY = "com.tinCoder.KEY";
    Button btnSendBroadcast ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_object_main);

        Button btnSendBroadcast = findViewById(R.id.btn_send_broadcast_Object);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadcast();
            }
        });
    }

   private void clickSendBroadcast () {
       Intent intent = new Intent(MY_ACTION);

       // Gửi 1 list user sang app khác
// Sử lý convert 1 Object thành 1 String để có thể chuyển vào intent để gửi qua Broadcast
//    User user = new User(1, "TinCoder");
//    Gson gson = new Gson();
//    String jsonUser = gson.toJson(user);
//       intent.putExtra(MY_KEY, jsonUser);

       // Gửi 1 list user sang app khác
       User user = new User(1, "TinCoder");
       User user1 = new User(1, "TinCoder1");
       List<User> list = new ArrayList<>();

       list.add(user);
       list.add(user1);

       Gson gson = new Gson();
       JsonArray  jsonArray = gson.toJsonTree(list).getAsJsonArray();
       String strJson = jsonArray.toString();

        intent.putExtra(MY_KEY, strJson);
        // ------

       sendBroadcast(intent);   // Gửi intent đi - phát ra 1 Broadcast
       Toast.makeText(this, "SendBroadCast success ", Toast.LENGTH_LONG).show();

    }
}