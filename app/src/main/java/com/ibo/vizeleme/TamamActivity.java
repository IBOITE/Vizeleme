package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TamamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamam);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();
    }
}