package com.example.android_broadcastreceiver.BroadcastReceiver_wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

// Cần khai báo ở bên AndroidManifest khai báo receiver
// Tạo 1 class extends từ BroadcastReceiver và khai báo ở AndroidManifest là có thể sử dụng được


public class ExampleBroadcastReceiver extends BroadcastReceiver {
// option + shift + enter
    @Override
    public void onReceive(Context context, Intent intent) {
if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
    if (isNetworkAvailable(context)){
        Toast.makeText(context, "Internet connected", Toast.LENGTH_LONG).show();
    }else{
        Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_LONG).show();
    }
}
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null){
            return false;
        }
        // from ablove android 23 (6) check diffirent

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if(network == null) {
                return false;
            }
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            Log.d("ABC", "isNetworkAvailable: " +capabilities );
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}

