package com.ibo.vizeleme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

import Controller.DataBaseHandler;
import Model.OgrenciInfor;

public class OgrenciActivity1 extends AppCompatActivity {
    TextView tvOgAd,tvOgSoyad,tvOgTC,tvOgDogum,tvOgNo;
    Spinner spOgTuru,spOgSinif;
    Button btnTamamla,btnGeri,btnOgBilgi;
    ImageButton qrOg;
    DataBaseHandler db;
    String qrdenGelenText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci1);

        //من اجل اخفاء الشريط الاعلى
        getSupportActionBar().hide();

        tvOgAd=findViewById(R.id.tvOgAd);
        tvOgSoyad=findViewById(R.id.tvOgSoyad);
        tvOgTC=findViewById(R.id.tvOgTC);
        tvOgDogum=findViewById(R.id.tvOgDogum);
        tvOgNo=findViewById(R.id.tvOgNO);
        spOgTuru=findViewById(R.id.spOgTuru);
        spOgSinif=findViewById(R.id.spOgSinif);
        btnTamamla=findViewById(R.id.btnOgTamamla);
        btnOgBilgi=findViewById(R.id.btnOgBilgi);
        btnGeri=findViewById(R.id.btnOgGeri);
        qrOg=findViewById(R.id.qrOg);
        db=new DataBaseHandler(this);


        Bundle bundle=getIntent().getExtras();
        int singID=bundle.getInt("loginID");

        final String[] spdenGelenTextTur = {""};
        final String[] spdenGelenTextSinif = {""};

        //Qr barcode read
        qrOg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });



        ArrayList<String> ogrenciTuruList=new ArrayList<>();
        ogrenciTuruList.add("Öğrenci Turu");
        ogrenciTuruList.add("Ilk Okul");
        ogrenciTuruList.add("Orta Okul");
        ogrenciTuruList.add("Lise");
        ogrenciTuruList.add("Lisans");
        ogrenciTuruList.add("Yüksek Lisans");
        ogrenciTuruList.add("Doktora");
        ArrayAdapter<String> adapterToOgTuru=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,ogrenciTuruList);
        spOgTuru.setAdapter(adapterToOgTuru);

        ArrayList<String> ogrenciSinifiList=new ArrayList<>();
        ogrenciSinifiList.add("Sınıf ya da Yıl");
        ogrenciSinifiList.add("1");
        ogrenciSinifiList.add("2");
        ogrenciSinifiList.add("3");
        ogrenciSinifiList.add("4");
        ogrenciSinifiList.add("5");
        ogrenciSinifiList.add("6");
        ogrenciSinifiList.add("7");
        ArrayAdapter<String> adapterToOgSinifi=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,ogrenciSinifiList);
        spOgSinif.setAdapter(adapterToOgSinifi);

        spOgTuru.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spdenGelenTextTur[0] =adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spOgSinif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spdenGelenTextSinif[0] =adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //اذا كانت احد الحقول فارغة
                if(tvOgAd.getText().toString().isEmpty()||tvOgSoyad.getText().toString().isEmpty()||tvOgTC.getText().toString().isEmpty()||tvOgDogum.getText().toString().isEmpty()||tvOgNo.getText().toString().isEmpty()){
                    btnTamamla.setError("Lütfen alanları doldurun");
                    Toast.makeText(OgrenciActivity1.this,"Lütfen alanları doldurun",Toast.LENGTH_LONG).show();
                }else{
                    db.addOgrenci(new OgrenciInfor(singID,tvOgAd.getText().toString(),tvOgSoyad.getText().toString(),tvOgTC.getText().toString(),spdenGelenTextTur[0],tvOgNo.getText().toString(),tvOgDogum.getText().toString()
                            ,spdenGelenTextSinif[0],qrdenGelenText));


                    Intent intent=new Intent(getApplicationContext(),BankActivity.class);
                    intent.putExtra("loginID",singID);
                    startActivity(intent);
                    finish();
                }



            }
        });

        //btn create dialog
        android.app.AlertDialog.Builder ogAlertDialog=new AlertDialog.Builder(OgrenciActivity1.this);

        btnOgBilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ogAlertDialog.setTitle("Durum Belgesi nedir?");
                ogAlertDialog.setMessage("Durum belgesi vatandaşın durumunu açıklamaktadır.\n"+
                        "Belgeler aşağıdaki gibi yüklenmesi zorunludur.\n" +
                        "Öğrenciler:öğrenci belgesi.\n" +
                        "Öğretim üyeleri : üniversite yada okulda öğretmenlik belgesi.\n" +
                        "Engelliler engelli durum belgesi(heyet raporu).\n" +
                        "60_65 yaş arası olanlara yaş T.C kimliği.\n" +
                        "Gazi veya şehit yakınıysa. \n" +
                        "Onu ispat eden belge.\n" +
                        "Herhangi bir devlet kurumlarında çalışan (belediye çalışanları PTT çalışanları, polis).\n" +
                        "Bu kurumlarda çalıştıklarını ispat eden belge.");
                ogAlertDialog.setCancelable(false);

                ogAlertDialog.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog=ogAlertDialog.create();
                dialog.show();

            }

        });





        btnGeri.setOnClickListener(new View.OnClickListener() {
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
        barLanuchOg.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLanuchOg=registerForActivityResult(new ScanContract(), result -> {
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