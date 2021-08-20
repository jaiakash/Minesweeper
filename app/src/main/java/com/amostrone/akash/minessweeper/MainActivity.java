package com.amostrone.akash.minessweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        Game gameView = new Game(this);
        linearLayout.addView(gameView);

        Button btn1 = (Button)findViewById(R.id.reset_button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage(getApplicationContext().getPackageName());
                ComponentName componentName = intent.getComponent();
                Intent mainIntent = Intent.makeRestartActivityTask(componentName);
                getApplicationContext().startActivity(mainIntent);
                Runtime.getRuntime().exit(0);
            }
        });
    }
}