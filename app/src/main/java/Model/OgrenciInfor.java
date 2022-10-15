package Model;

public class OgrenciInfor {
    private int id;
    private int idSignin;
    private String ogAd;
    private String ogSoyad;
    private String ogTC;
    private String ogTuru;
    private String ogNo;
    private String ogDogum;
    private String ogSinif;
    private String ogScan;

    public OgrenciInfor(int id, int idSignin, String ogAd, String ogSoyad, String ogTC, String ogTuru, String ogNo, String ogDogum, String ogSinif, String ogScan) {
        this.id = id;
        this.idSignin = idSignin;
        this.ogAd = ogAd;
        this.ogSoyad = ogSoyad;
        this.ogTC = ogTC;
        this.ogTuru = ogTuru;
        this.ogNo = ogNo;
        this.ogDogum = ogDogum;
        this.ogSinif = ogSinif;
        this.ogScan = ogScan;
    }

    public OgrenciInfor(int idSignin, String ogAd, String ogSoyad, String ogTC, String ogTuru, String ogNo, String ogDogum, String ogSinif, String ogScan) {
        this.idSignin = idSignin;
        this.ogAd = ogAd;
        this.ogSoyad = ogSoyad;
        this.ogTC = ogTC;
        this.ogTuru = ogTuru;
        this.ogNo = ogNo;
        this.ogDogum = ogDogum;
        this.ogSinif = ogSinif;
        this.ogScan = ogScan;
    }

    public int getIdSignin() {
        return idSignin;
    }

    public void setIdSignin(int idSignin) {
        this.idSignin = idSignin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOgAd() {
        return ogAd;
    }

    public void setOgAd(String ogAd) {
        this.ogAd = ogAd;
    }

    public String getOgSoyad() {
        return ogSoyad;
    }

    public void setOgSoyad(String ogSoyad) {
        this.ogSoyad = ogSoyad;
    }

    public String getOgTC() {
        return ogTC;
    }

    public void setOgTC(String ogTC) {
        this.ogTC = ogTC;
    }

    public String getOgTuru() {
        return ogTuru;
    }

    public void setOgTuru(String ogTuru) {
        this.ogTuru = ogTuru;
    }

    public String getOgNo() {
        return ogNo;
    }

    public void setOgNo(String ogNo) {
        this.ogNo = ogNo;
    }

    public String getOgDogum() {
        return ogDogum;
    }

    public void setOgDogum(String ogDogum) {
        this.ogDogum = ogDogum;
    }

    public String getOgSinif() {
        return ogSinif;
    }

    public void setOgSinif(String ogSinif) {
        this.ogSinif = ogSinif;
    }

    public String getOgScan() {
        return ogScan;
    }

    public void setOgScan(String ogScan) {
        this.ogScan = ogScan;
    }
}
