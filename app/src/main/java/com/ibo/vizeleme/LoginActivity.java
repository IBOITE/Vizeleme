package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Controller.DataBaseHandler;


public class LoginActivity extends AppCompatActivity {
    //تعريف الاشياء الموجودة ب xml
    TextView user,pass;
    Button BTNlogIn,BTNsingUP;
    DataBaseHandler baseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();



        //من اجل ربط الكائن بين xml  و java عن طريق id
        BTNsingUP=findViewById(R.id.btnToSingUp);
        user=findViewById(R.id.userLogin);
        pass=findViewById(R.id.passLogin);
        BTNlogIn=findViewById(R.id.btnLogin);
        baseHandler=new DataBaseHandler(this);


        //login btn
        BTNlogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=user.getText().toString();
                String passWord=pass.getText().toString();
                //اذا كانت احد الحقول فارغة
                if(userName.isEmpty()||passWord.isEmpty()){
                    BTNlogIn.setError("Lütfen alanları doldurun");
                    Toast.makeText(LoginActivity.this, "Lütfen alanları doldurun", Toast.LENGTH_LONG).show();

                }else{
                    int userID=baseHandler.getSubUser(userName).getId();
                    int passID=baseHandler.getSubPass(passWord).getId();
                    if(userID==passID){
                        Intent intent=new Intent(getApplicationContext(),chooseCardActivity.class);
                        intent.putExtra("loginID",userID);
                        startActivity(intent);
                        finish();
                    }

// If the username or password is not found
//                    if(userName.equals(baseHandler.getSubUser(userName))&&passWord.equals(baseHandler.getSubPass(passWord))){
//                        int userID=baseHandler.getSubUser(userName).getId();
//                        int passID=baseHandler.getSubPass(passWord).getId();
//                        if(userID==passID){
//                            Intent intent=new Intent(getApplicationContext(),chooseCardActivity.class);
//                            intent.putExtra("loginID",userID);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }else {
//                        BTNlogIn.setError("kullancı adı yada şifre yanlış");
//                        Toast.makeText(getApplicationContext(),"kullancı adı yada şifre yanlış",Toast.LENGTH_LONG).show();
//                    }





                }




            }
        });


        //sing up btn
        BTNsingUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getApplicationContext(),singUpActivity.class);
                startActivity(intent);

            }
        });




    }
}