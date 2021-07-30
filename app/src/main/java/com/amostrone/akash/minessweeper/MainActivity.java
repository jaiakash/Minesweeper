package com.amostrone.akash.minessweeper;

import androidx.appcompat.app.AppCompatActivity;
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
    }
}