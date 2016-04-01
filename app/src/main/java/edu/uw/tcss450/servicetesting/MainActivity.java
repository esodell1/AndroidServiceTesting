package edu.uw.tcss450.servicetesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clickStart(View view) {
        Toast.makeText(getApplicationContext(), "Starting service...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MonitorService.class);
        intent.setAction(MonitorService.ACTION_BAZ);
        startService(intent);
    }

    public void clickStop(View view) {
        Toast.makeText(getApplicationContext(), "Stopping service...", Toast.LENGTH_SHORT).show();
    }
}
