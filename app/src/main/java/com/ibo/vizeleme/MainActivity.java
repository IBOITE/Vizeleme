package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import Controller.DataBaseHandler;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();










        //Hello Activity
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();



    }
}