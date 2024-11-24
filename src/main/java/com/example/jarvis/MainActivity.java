package com.example.jarvis;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isServiceRunning = false; // Tracks service status
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(v -> {
            if (!isServiceRunning) {
                startJarvisService();
            } else {
                Toast.makeText(this, "Jarvis is already running!", Toast.LENGTH_SHORT).show();
            }
        });

        stopButton.setOnClickListener(v -> {
            if (isServiceRunning) {
                stopJarvisService();
            } else {
                Toast.makeText(this, "Jarvis is not running.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startJarvisService() {
        Intent intent = new Intent(this, JarvisService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isServiceRunning = true;
                Toast.makeText(MainActivity.this, "Jarvis started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isServiceRunning = false;
            }
        };

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        startService(intent);
    }

    private void stopJarvisService() {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }

        Intent intent = new Intent(this, JarvisService.class);
        stopService(intent);

        isServiceRunning = false;
        Toast.makeText(this, "Jarvis stopped!", Toast.LENGTH_SHORT).show();
    }
                  }
          
