package com.example.eworkers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ConnectivityReceiver extends BroadcastReceiver {
    private Intent serviceIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity) {
                if (AuthenticationService.isIsRunning() == true) {
                    try {
                        context.stopService(serviceIntent);
                        AuthenticationService.setIsRunning(false);
                    } catch (Exception e) {
                        System.out.println("Service intent is null.");
                    }
                } else { //no internet connection
                    Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT).show();
                }
            } else {
                serviceIntent = new Intent(context, AuthenticationService.class);
                context.startService(serviceIntent);
            }
        }
    }
}
