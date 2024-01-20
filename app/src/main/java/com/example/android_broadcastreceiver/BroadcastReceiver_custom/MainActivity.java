// BroadcastReceiver tincoder
// Phát ra sự kiện và nhận sự kiện đó trong cùng 1 app

// https://www.youtube.com/watch?v=_aTKGDUSTzA&list=PL3Ob3F0T-08be16h_I98K7lB7ba70q6bi&index=4
// Truyển Object từ ứng dụng này sang ứng dụng khác

package com.example.android_broadcastreceiver.BroadcastReceiver_custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_broadcastreceiver.BroadcastReceiver_wifi.ExampleBroadcastReceiver;
import com.example.android_broadcastreceiver.R;

public class MainActivity extends AppCompatActivity {
    private static final String MY_ACTION = "com.tinCoder.ACTION";
    private static final String MY_KEY = "com.tinCoder.TEXT";
    private ExampleBroadcastReceiver exampleBroadcast;  //  Khao báo biến
    private Button btnSendBroadcast;
    private TextView tvReceived;

    // Khai báo 1 Broadcast để có thể nhận sự kiện đó - Đăng ký và huỷ Broadcast đó
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(MY_KEY);
                tvReceived.setText(text);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exampleBroadcast = new ExampleBroadcastReceiver();  // Gán giá trị cho biến

        btnSendBroadcast= findViewById(R.id.btn_send_broadcast);
        tvReceived = findViewById(R.id.tv_received);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadcast();
            }
        });


    }

    // Send đi 1 Broadcast - Đối tượng gửi đi là 1 intent
    private void clickSendBroadcast(){
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_KEY, "This is TinCoder channel ");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver( exampleBroadcast, intentFilter);  //ĐĂng ký lắng nghe 1 broadcast Receiver

        IntentFilter intentFilter_custom = new IntentFilter(MY_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter_custom);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(exampleBroadcast); //Huỷ lắng nghe Broadcast
        unregisterReceiver(mBroadcastReceiver); //Huỷ lắng nghe Broadcast
        // Khi mà app chạy background : Đã huỷ đăng ký rồi
    }

    // Hoàn toàn có thể điều khiển việc huỷ đăng ký của Broadcast Receiver

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(exampleBroadcast);   // Khi mà app bị kill đi mới huỷ đăng ký
    }
}


// Gửi sự kiện từ APP 1 sang App 2 hoàn toàn có thể sử dụng Broadcast vì Broadcast này là  chung của 1 hệ thống 1 hệ điều hành
// Không cần phải khai báo app gửi app nhận làm gì
// cứ gửi -- App Khác mà đăng ký nhận Broadcast mà có trùng Action thì có thể nhận được 