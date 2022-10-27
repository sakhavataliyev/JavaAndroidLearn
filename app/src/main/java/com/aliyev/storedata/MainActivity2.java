package com.aliyev.storedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textViewfirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewfirst = findViewById(R.id.TextViewFirst);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userInput");
        textViewfirst.setText(userName);

    }


    public void changeScreen(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}