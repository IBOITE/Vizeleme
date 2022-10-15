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

public class IndirmliActivity extends AppCompatActivity {

    TextView tvInAd,tvInSoyAd,tvInTC,tvInDogum;
    Button btnInTamamla,btnInGeri,btnInBilgi;
    ImageButton qrIn;
    DataBaseHandler db;
    String qrdenGelenText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indirmli);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();

        tvInAd=findViewById(R.id.tvInAd);
        tvInSoyAd=findViewById(R.id.tvInSoyad);
        tvInTC=findViewById(R.id.tvInTC);
        tvInDogum=findViewById(R.id.tvInDogum);
        btnInTamamla=findViewById(R.id.btnInTamamla);
        btnInBilgi=findViewById(R.id.btnInBilgi);
        btnInGeri=findViewById(R.id.btnInGeri);
        qrIn=findViewById(R.id.qrIn);
        db=new DataBaseHandler(this);
        Bundle bundle=getIntent().getExtras();
        int singID=bundle.getInt("loginID");

        //Qr barcode read
        qrIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        btnInTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //اذا كانت احد الحقول فارغة
                if(tvInAd.getText().toString().isEmpty()||tvInSoyAd.getText().toString().isEmpty()||tvInTC.getText().toString().isEmpty()||tvInDogum.getText().toString().isEmpty()){

                    btnInTamamla.setError("Lütfen alanları doldurun");
                    Toast.makeText(IndirmliActivity.this,"Lütfen alanları doldurun",Toast.LENGTH_LONG).show();
                }else{
                    db.addSerbest(new SerbestInfor(singID,tvInAd.getText().toString(),tvInSoyAd.getText().toString(),tvInTC.getText().toString(),tvInDogum.getText().toString(),qrdenGelenText));
                    Intent intent=new Intent(getApplicationContext(),BankActivity.class);
                    intent.putExtra("loginID",singID);
                    startActivity(intent);
                    finish();
                }


            }
        });

        //btn create dialog
        android.app.AlertDialog.Builder inAlertDialog=new AlertDialog.Builder(IndirmliActivity.this);

        btnInBilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inAlertDialog.setTitle("Durum Belgesi nedir?");
                inAlertDialog.setMessage("Durum belgesi vatandaşın durumunu açıklamaktadır.\n"+
                        "Belgeler aşağıdaki gibi yüklenmesi zorunludur.\n" +
                        "Öğrenciler:öğrenci belgesi.\n" +
                        "Öğretim üyeleri : üniversite yada okulda öğretmenlik belgesi.\n" +
                        "Engelliler engelli durum belgesi(heyet raporu).\n" +
                        "60_65 yaş arası olanlara yaş T.C kimliği.\n" +
                        "Gazi veya şehit yakınıysa. \n" +
                        "Onu ispat eden belge.\n" +
                        "Herhangi bir devlet kurumlarında çalışan (belediye çalışanları PTT çalışanları, polis).\n" +
                        "Bu kurumlarda çalıştıklarını ispat eden belge.");
                inAlertDialog.setCancelable(false);

                inAlertDialog.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog=inAlertDialog.create();
                dialog.show();

            }

        });

        btnInGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IndirmliActivity.this,chooseCardActivity.class);
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
        barLanuchIn.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLanuchIn=registerForActivityResult(new ScanContract(), result -> {
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