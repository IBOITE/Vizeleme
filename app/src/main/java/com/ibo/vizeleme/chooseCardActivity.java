package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class chooseCardActivity extends AppCompatActivity {
    //تعريف الاشياء الموجودة ب xml

    ImageButton ogrenciBTn,serbestBTn,indirmliBTn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();



        //من اجل ربط الكائن بين xml  و java عن طريق id
        setContentView(R.layout.activity_choose_card);
        ogrenciBTn=findViewById(R.id.btnStuedent);
        serbestBTn=findViewById(R.id.btnDisabled);
        indirmliBTn=findViewById(R.id.btnOld);

        //ينقل ID يلي مسجل دخول
        Bundle bundle=getIntent().getExtras();
        int singID=bundle.getInt("loginID");

        // OGRENCI BTN
        ogrenciBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),OgrenciActivity1.class);
                intent.putExtra("loginID",singID);
                startActivity(intent);
                finish();
            }
        });

        //SERBEST BTN
        serbestBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SerbestActivity.class);
                intent.putExtra("loginID",singID);
                startActivity(intent);
                finish();
            }
        });



        //INDIRMLI BTN
        indirmliBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),IndirmliActivity.class);
                intent.putExtra("loginID",singID);
                startActivity(intent);
                finish();
            }
        });
    }
}