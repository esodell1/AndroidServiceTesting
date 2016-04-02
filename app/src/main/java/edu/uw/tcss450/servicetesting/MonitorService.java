package edu.uw.tcss450.servicetesting;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;


public class MonitorService extends IntentService {
    private static final String LOG_TAG = "MonitorService";
    private static final int NOTIFY_GEN_SERVICE = 1;

    public MonitorService() {
        super("MonitorService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String mac = intent.getStringExtra(MainActivity.MAC_ADDR_PARAM);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
            mBuilder.setSmallIcon(R.drawable.ic_ethernet_black);
            mBuilder.setContentTitle("HR/SpO2 Monitor");
            mBuilder.setContentText("Device communication service is running.");
            mBuilder.setContentIntent(
                    PendingIntent.getActivity(
                            getApplicationContext(),
                            0,
                            new Intent(this, MainActivity.class),
                            PendingIntent.FLAG_UPDATE_CURRENT));

            this.startForeground(NOTIFY_GEN_SERVICE, mBuilder.build());
            connectToDeviceMAC(mac);
        }
    }

    private int connectToDeviceMAC(String macAddr) {

        // Verify the Android device is connected to WiFi
        Log.v(LOG_TAG, "Verifying WiFi connection...");
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mWifi.isConnected()) {
            Log.e(LOG_TAG, "WiFi connection not detected. Aborting service...");
            stopSelf();
        } else {
            Log.v(LOG_TAG, "WiFi found.");
        }


        // Connect to MAC address
        Log.v(LOG_TAG, "Connecting to device " + macAddr + "...");



        for(int i = 0; i < 3; i++) {
            // Service stays alive when there is work, otherwise it will terminate.
            try {
                Thread.sleep(5000);
                Log.v(LOG_TAG, "Doing some more work...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public void onDestroy() {
        Log.v(LOG_TAG, "Stopping service thread.");
        super.onDestroy();
    }
}
