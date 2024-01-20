package com.example.android_broadcastreceiver.BroadcastReceiver_Explicit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String MY_KEY = "com.tinCoder.KEY";
    @Override
    public void onReceive(Context context, Intent intent) {
        String myText = intent.getStringExtra(MY_KEY);
        Toast.makeText(context, myText, Toast.LENGTH_SHORT).show();
    }
}
