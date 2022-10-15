package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import Model.BankInfor;
import Model.IndirmliInfor;
import Model.OgrenciInfor;
import Model.SerbestInfor;
import Model.SingUpInfor;
import Model.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler( Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String  CREATE_SINGIN="CREATE TABLE "+Util.TABLE_NAME+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_NAME+" TEXT UNIQUE, "
               +Util.KEY_EMAIL+" TEXT UNIQUE, "+Util.KEY_PASS+" TEXT, "+Util.KEY_PASS_CON+" TEXT )";

       String CREATE_OGRENCI="CREATE TABLE "+Util.TABLE_OGERNCI+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_AD+" TEXT, "+Util.KEY_SOYAD+" TEXT, "+Util.KEY_TC+" TEXT UNIQUE, "
               +Util.KEY_OG_TURU+" TEXT, "+Util.KEY_OG_NO+" TEXT UNIQUE, "+Util.KEY_DOGUM+" TEXT, "+Util.KEY_SINIF+" TEXT, "+Util.KEY_SCAN+" TEXT, "+Util.KEY_REFRANS+" INTEGER REFERENCES "
               +Util.TABLE_NAME+" ( "+Util.KEY_ID+" ) " +" )";
       String CREATE_SERBEST="CREATE TABLE "+Util.TABLE_SERBEST+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_AD+" TEXT, "+Util.KEY_SOYAD+" TEXT, "+Util.KEY_TC+" TEXT UNIQUE, "
               +Util.KEY_DOGUM+" TEXT, "+Util.KEY_SCAN+" TEXT, "+Util.KEY_REFRANS+" INTEGER REFERENCES "
               +Util.TABLE_NAME+" ( "+Util.KEY_ID+" ) " +" )";
       String CREATE_INDIRIMLI="CREATE TABLE "+Util.TABLE_INDIRIMLI+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_AD+" TEXT, "+Util.KEY_SOYAD+" TEXT, "+Util.KEY_TC+" TEXT UNIQUE, "
               +Util.KEY_DOGUM+" TEXT, "+Util.KEY_SCAN+" TEXT, "+Util.KEY_REFRANS+" INTEGER REFERENCES "
               +Util.TABLE_NAME+" ( "+Util.KEY_ID+" ) " +" )";
       String CREATE_BANK="CREATE TABLE "+Util.TABLE_BANK+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_KART_NUM+" TEXT, "+Util.KEY_KART_ISMI+" TEXT, "+Util.KEY_CVV+" TEXT, "+Util.KEY_SON_KULLANMA+" TEXT, "
               +Util.KEY_REFRANS+" INTEGER REFERENCES "+Util.TABLE_NAME+" ( "+Util.KEY_ID+" ) " +" )";

        sqLiteDatabase.execSQL(CREATE_SINGIN);
        sqLiteDatabase.execSQL(CREATE_OGRENCI);
        sqLiteDatabase.execSQL(CREATE_SERBEST);
        sqLiteDatabase.execSQL(CREATE_INDIRIMLI);
        sqLiteDatabase.execSQL(CREATE_BANK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_OGERNCI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_SERBEST);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_INDIRIMLI);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_BANK);
        onCreate(sqLiteDatabase);
    }

    //signUp yaptiginda veritabaninia yeni uye ekliyor
    public void addSub(SingUpInfor singIn_infor){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,singIn_infor.getUserName());
        values.put(Util.KEY_EMAIL,singIn_infor.getEmail());
        values.put(Util.KEY_PASS,singIn_infor.getPass());
        values.put(Util.KEY_PASS_CON,singIn_infor.getPassCon());
        database.insert(Util.TABLE_NAME,null,values);
        database.close();
    }

    //veritaabanindan bir uye gitiryor ,username dayali ((signin islemi icin))
    public SingUpInfor getSubUser(String userName){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_EMAIL,Util.KEY_PASS,Util.KEY_PASS_CON},Util.KEY_NAME+"=?",new String[]{userName}
                ,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        SingUpInfor singIn_infor=new SingUpInfor(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return singIn_infor;

    }





    //veritaabanindan bir uye gitiryor ,password dayali ((signin islemi icin))
    public SingUpInfor getSubPass(String pass){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_EMAIL,Util.KEY_PASS,Util.KEY_PASS_CON},Util.KEY_PASS+"=?",new String[]{pass}
                ,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        SingUpInfor singIn_infor=new SingUpInfor(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return singIn_infor;


    }



    //OGRENCI TABLOSUNA YENI VERI EKLIYOR
    public void addOgrenci(OgrenciInfor ogrenciInfor){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_AD,ogrenciInfor.getOgAd());
        values.put(Util.KEY_SOYAD,ogrenciInfor.getOgSoyad());
        values.put(Util.KEY_TC,ogrenciInfor.getOgTC());
        values.put(Util.KEY_OG_TURU,ogrenciInfor.getOgTuru());
        values.put(Util.KEY_OG_NO,ogrenciInfor.getOgNo());
        values.put(Util.KEY_DOGUM,ogrenciInfor.getOgDogum());
        values.put(Util.KEY_SINIF,ogrenciInfor.getOgSinif());
        values.put(Util.KEY_SCAN,ogrenciInfor.getOgScan());
        values.put(Util.KEY_REFRANS,ogrenciInfor.getIdSignin());
        database.insert(Util.TABLE_OGERNCI,null,values);
        database.close();
    }

    //SERBEST TABLOSUNA YENI VERI EKLIYOR
    public void addSerbest(SerbestInfor serbestInfor){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_AD,serbestInfor.getOgAd());
        values.put(Util.KEY_SOYAD,serbestInfor.getOgSoyad());
        values.put(Util.KEY_TC,serbestInfor.getOgTC());
        values.put(Util.KEY_DOGUM,serbestInfor.getOgDogum());
        values.put(Util.KEY_SCAN,serbestInfor.getOgScan());
        values.put(Util.KEY_REFRANS,serbestInfor.getIdSignin());
        database.insert(Util.TABLE_SERBEST,null,values);
        database.close();
    }

    //INDIRIMLI TABLOSUNA YENI VERI EKLIYOR
    public void addIndirimli(IndirmliInfor indirmliInfor){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_AD,indirmliInfor.getOgAd());
        values.put(Util.KEY_SOYAD,indirmliInfor.getOgSoyad());
        values.put(Util.KEY_TC,indirmliInfor.getOgTC());
        values.put(Util.KEY_DOGUM,indirmliInfor.getOgDogum());
        values.put(Util.KEY_SCAN,indirmliInfor.getOgScan());
        values.put(Util.KEY_REFRANS,indirmliInfor.getIdSignin());
        database.insert(Util.TABLE_SERBEST,null,values);
        database.close();
    }

    //BANK TABLOSUNA YENI VERI EKLIYOR
    public void addBank(BankInfor bankInfor){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_KART_ISMI,bankInfor.getKartIsmi());
        values.put(Util.KEY_KART_NUM,bankInfor.getKartNum());
        values.put(Util.KEY_SON_KULLANMA,bankInfor.getSonKullanma());
        values.put(Util.KEY_CVV,bankInfor.getCVV());
        values.put(Util.KEY_REFRANS,bankInfor.getIdSignin());
        database.insert(Util.TABLE_BANK,null,values);
        database.close();
    }





}
