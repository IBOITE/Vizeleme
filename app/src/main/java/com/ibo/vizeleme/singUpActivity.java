package com.ibo.vizeleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.DataBaseHandler;
import Model.SingUpInfor;

public class singUpActivity extends AppCompatActivity {
    //تعريف الاشياء الموجودة ب xml
    EditText User,Email,Password,PassWordCon;
    Button BtnSingUp;
    DataBaseHandler baseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();



        //من اجل ربط الكائن بين xml  و java عن طريق id
        User=findViewById(R.id.userSingUp);
        Email=findViewById(R.id.emailSingUp);
        Password=findViewById(R.id.passSingUp);
        PassWordCon=findViewById(R.id.passConSingUp);
        BtnSingUp=findViewById(R.id.btnSingUp);
        baseHandler=new DataBaseHandler(this);






        //sing up button
        BtnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //قالب من اجل وضع شروط عند ادخال الايميل
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher mat=pattern.matcher(Email.getText().toString());
                //من اجل عدم تكرار اسم المستخدم
//                if(User.getText().toString().equals(baseHandler.getSubUser(User.getText().toString()).getUserName())){
//                    User.setError("bu user name daha önce kullanmış!!");
//                    Toast.makeText(singUpActivity.this, "bu user name daha önce kullanmış!!", Toast.LENGTH_LONG).show();
//
//                }else{}
                    //اذا كانت احد الحقول فارغة
                    if(User.getText().toString().isEmpty()||Email.getText().toString().isEmpty()||Password.getText().toString().isEmpty()||PassWordCon.getText().toString().isEmpty()){
                        BtnSingUp.setError("");
                        Toast.makeText(singUpActivity.this, "Lütfen alanları doldurun", Toast.LENGTH_LONG).show();

                    }else{

                        //من اجل المساوة بين الحقلين تبع كلمة المرور
                        if(Password.getText().toString().equals(PassWordCon.getText().toString())){

                            //من اجل فحص حق الايميل
                            if(mat.matches()){
                                baseHandler.addSub(new SingUpInfor(User.getText().toString(),Email.getText().toString(),Password.getText().toString(),PassWordCon.getText().toString()));
                                Toast.makeText(singUpActivity.this, "işlem tammalandı", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(singUpActivity.this, "Email invalid", Toast.LENGTH_LONG).show();
                                Email.setError("Email invalid");

                            }

                        }else {
                            Toast.makeText(singUpActivity.this, "passward yanlış", Toast.LENGTH_LONG).show();
                            PassWordCon.setError("passward yanlış");


                        }

                    }








            }
        });
    }
}