package edu.uw.tcss450.servicetesting;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MAC_ADDR_PARAM = "MAC_ADDR_PARAM";
    private static final int NOTIFY_GEN_SERVICE = 1;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStart(View view) {
        Toast.makeText(getApplicationContext(), "Starting service...", Toast.LENGTH_SHORT).show();
        EditText mac = (EditText) findViewById(R.id.editMAC);
        String macAddr = mac.getText().toString();
        if (macAddr.length() != 17) {
            macAddr = "C0:EE:FB:21:53:59";
        }
        serviceIntent = new Intent(this, MonitorService.class);
        serviceIntent.putExtra(MAC_ADDR_PARAM, macAddr);

        startService(serviceIntent);



    }

    public void clickStop(View view) {
        Toast.makeText(getApplicationContext(), "Stopping service...", Toast.LENGTH_SHORT).show();
        if(serviceIntent != null) {
            stopService(serviceIntent);
        }
    }

}
