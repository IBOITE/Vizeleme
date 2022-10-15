package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Controller.DataBaseHandler;
import Model.BankInfor;

public class BankActivity extends AppCompatActivity {
    TextView kartNum,kartIsmi,sonKullanm,cvv;
    Button btnOde;
    DataBaseHandler baseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();

        setContentView(R.layout.activity_bank);
        kartNum=findViewById(R.id.kartNumID);
        kartIsmi=findViewById(R.id.kartNumID);
        sonKullanm=findViewById(R.id.kartNumID);
        cvv=findViewById(R.id.kartNumID);
        btnOde=findViewById(R.id.tmmBtn);
        Bundle bundle=getIntent().getExtras();
        int singID=bundle.getInt("loginID");
        baseHandler=new DataBaseHandler(this);

        btnOde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //اذا كانت احد الحقول فارغة
                if(kartNum.getText().toString().isEmpty()||kartIsmi.getText().toString().isEmpty()||sonKullanm.getText().toString().isEmpty()||cvv.getText().toString().isEmpty()){

                    btnOde.setError("Lütfen alanları doldurun");
                    Toast.makeText(BankActivity.this,"Lütfen alanları doldurun",Toast.LENGTH_LONG).show();
                }else{

                    baseHandler.addBank(new BankInfor(singID,kartNum.getText().toString(),sonKullanm.getText().toString(),cvv.getText().toString(),cvv.getText().toString()));
                    Intent intent=new Intent(getApplicationContext(),TamamActivity.class);
                    startActivity(intent);
                }



            }
        });

    }
}