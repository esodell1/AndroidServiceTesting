package edu.uw.tcss450.servicetesting;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class MonitorService extends IntentService {

    public MonitorService() {
        super("MonitorService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String param1 = intent.getStringExtra(MainActivity.MAC_ADDR_PARAM);
            handleActionBaz(param1);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1) {
        // TODO: Handle action Baz
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.v("serviceResponse", "Running BAZ function...");
        Log.v("serviceResponse", "Connecting to device " + param1 + "...");

        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDestroy() {
        Log.v("serviceResponse", "Stopping service thread.");
        super.onDestroy();
    }
}
