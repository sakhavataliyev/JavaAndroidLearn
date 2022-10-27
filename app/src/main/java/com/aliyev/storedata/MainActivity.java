package com.aliyev.storedata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewCount;

    String userName;
    EditText editTextFirst;

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Countdownstart
        textViewCount = findViewById(R.id.textViewCount);

        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long l) {
                textViewCount.setText("Left: " + l/1000);

            }

            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(), "Done Count!", Toast.LENGTH_LONG).show();
                textViewCount.setText("Finished!");
            }
        }.start();

//CountdownFinish

//        Toast.makeText(MainActivity.this, "Saved Test", Toast.LENGTH_LONG).show();

        editTextFirst = findViewById(R.id.editTextFirst);

        userName = "";

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.aliyev.storedata", Context.MODE_PRIVATE);

        int storAge = sharedPreferences.getInt("storedData", 0);

        if (storAge == 0) {
            textView.setText("Your Age: ");
        }
        else {
            textView.setText("Your Age: " + storAge);
        }


    }


    public void changeActivity(View view) {

        userName = editTextFirst.getText().toString();

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        intent.putExtra("userInput", userName);

        startActivity(intent);



    }





    public void save(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Save");
        alert.setMessage("Are You Sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                    if (!editText.getText().toString().matches("")){
                    int userAge = Integer.parseInt(editText.getText().toString());
                    textView.setText("Your Age: " + userAge);

                    sharedPreferences.edit().putInt("storedData", userAge).apply();

                }

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();

//        if (!editText.getText().toString().matches("")){
//            int userAge = Integer.parseInt(editText.getText().toString());
//            textView.setText("Your Age: " + userAge);
//
//            sharedPreferences.edit().putInt("storedData", userAge).apply();
//        }
    }

    public void delete(View view) {

        int storeData = sharedPreferences.getInt("storedData", 0);

        if (storeData != 0){
            sharedPreferences.edit().remove("storedData").apply();
            textView.setText("Your Age:");
        }
    }


}