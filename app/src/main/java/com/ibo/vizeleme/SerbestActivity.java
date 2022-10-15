package com.ibo.vizeleme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import Controller.DataBaseHandler;
import Model.SerbestInfor;

public class SerbestActivity extends AppCompatActivity {
    TextView tvSrAd,tvSrSoyAd,tvSrTC,tvSrDogum;
    Button btnSrTamamla,btnSrGeri,btnSrBilgi;
    ImageButton qrSr;
    DataBaseHandler db;
    String qrdenGelenText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serbest);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();

        tvSrAd=findViewById(R.id.tvSrAd);
        tvSrSoyAd=findViewById(R.id.tvSrSoyad);
        tvSrTC=findViewById(R.id.tvSrTC);
        tvSrDogum=findViewById(R.id.tvSrDogum);
        btnSrTamamla=findViewById(R.id.btnSrTamamla);
        btnSrGeri=findViewById(R.id.btnSrGeri);
        btnSrBilgi=findViewById(R.id.btnSrBilgi);
        qrSr=findViewById(R.id.qrSr);
        db=new DataBaseHandler(this);
        Bundle bundle=getIntent().getExtras();
        int singID=bundle.getInt("loginID");




        //Qr barcode read
        qrSr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        btnSrTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //اذا كانت احد الحقول فارغة
                if(tvSrAd.getText().toString().isEmpty()||tvSrSoyAd.getText().toString().isEmpty()||tvSrTC.getText().toString().isEmpty()||tvSrDogum.getText().toString().isEmpty()){
                    btnSrTamamla.setError("Lütfen alanları doldurun");
                    Toast.makeText(SerbestActivity.this,"Lütfen alanları doldurun",Toast.LENGTH_LONG).show();
                }else{
                    db.addSerbest(new SerbestInfor(singID,tvSrAd.getText().toString(),tvSrSoyAd.getText().toString(),tvSrTC.getText().toString(),tvSrDogum.getText().toString(),qrdenGelenText));
                    Intent intent=new Intent(getApplicationContext(),BankActivity.class);
                    intent.putExtra("loginID",singID);
                    startActivity(intent);
                    finish();
                }


            }
        });
        //btn create dialog
        android.app.AlertDialog.Builder srAlertDialog=new android.app.AlertDialog.Builder(SerbestActivity.this);

        btnSrBilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srAlertDialog.setTitle("Durum Belgesi nedir?");
                srAlertDialog.setMessage("Durum belgesi vatandaşın durumunu açıklamaktadır.\n"+
                        "Belgeler aşağıdaki gibi yüklenmesi zorunludur.\n" +
                        "Öğrenciler:öğrenci belgesi.\n" +
                        "Öğretim üyeleri : üniversite yada okulda öğretmenlik belgesi.\n" +
                        "Engelliler engelli durum belgesi(heyet raporu).\n" +
                        "60_65 yaş arası olanlara yaş T.C kimliği.\n" +
                        "Gazi veya şehit yakınıysa. \n" +
                        "Onu ispat eden belge.\n" +
                        "Herhangi bir devlet kurumlarında çalışan (belediye çalışanları PTT çalışanları, polis).\n" +
                        "Bu kurumlarda çalıştıklarını ispat eden belge.");
                srAlertDialog.setCancelable(false);

                srAlertDialog.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog=srAlertDialog.create();
                dialog.show();

            }

        });

        btnSrGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),chooseCardActivity.class);
                intent.putExtra("loginID",singID);
                startActivity(intent);
                finish();
            }
        });
    }

    private void scanCode() {
        ScanOptions options=new ScanOptions();
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLanuchSr.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLanuchSr=registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            qrdenGelenText=result.getContents();
            //التحكم في النتيجة  result.getContents
//            AlertDialog.Builder builder=new AlertDialog.Builder(SerbestActivity.this);
//            builder.setTitle("result");
//            builder.setMessage(result.getContents());
//            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();
        }
    });
}