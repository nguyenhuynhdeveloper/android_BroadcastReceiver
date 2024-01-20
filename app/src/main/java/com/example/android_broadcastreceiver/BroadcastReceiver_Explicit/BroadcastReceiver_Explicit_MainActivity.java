package com.example.android_broadcastreceiver.BroadcastReceiver_Explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_broadcastreceiver.R;

public class BroadcastReceiver_Explicit_MainActivity extends AppCompatActivity {
    private static final String MY_ACTION = "com.tinCoder.ACTION";
    private static final String MY_KEY = "com.tinCoder.KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver_explicit_activity_main);
        Button btnSendBroadcast_explicit = findViewById(R.id.btn_send_broadcast_Explicit);
        btnSendBroadcast_explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadcast();
            }
        });
    }
    private void clickSendBroadcast () {
        Intent intent = new Intent();

        // Đoạn thêm vào để quy định chính xác sẽ gửi Broadcast đi đâu
        // Cách 1: mấu chốt là tạo ComponentName
//        Intent intent = new Intent(this, MyBroadcastReceiver.class ); // Tham số thứ 2 chỉ định cái mà sẽ nhận sự kiện Broadcast

        // Cách 2:
//        intent.setClass(this, MyBroadcastReceiver.class);

        //  Cách 3: Tạo 1 ComponentName
//        Gửi cho cùng App
//        ComponentName componentName = new ComponentName(this, MyBroadcastReceiver.class);
        // Gửi cho khác App
        ComponentName componentName= new ComponentName("com.example.android_basic",  // Tham số đầu là packageName của Project nhận Broadcast
                "com.example.android_basic.BroadcastReceiver_demoReceiver.MyBroadcastReceiver2");
        intent.setComponent(componentName);


        intent.putExtra(MY_KEY, "This is TinCoder channel");
        sendBroadcast(intent);
    }
}

// Hoàn có thể gửi 1 sự kiện cho 1 thằng cụ thể nằm ngoài Project -- ứng dụng khác
/**
ở Manifest phải để android:exported = "true"  để có thể export ra ngoài ứng dụng hiện tại
 */