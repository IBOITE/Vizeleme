package Model;

public class IndirmliInfor {

    private int id;
    private int idSignin;
    private String ogAd;
    private String ogSoyad;
    private String ogTC;
    private String ogDogum;
    private String ogScan;

    public IndirmliInfor(int id, int idSignin, String ogAd, String ogSoyad, String ogTC, String ogDogum, String ogScan) {
        this.id = id;
        this.idSignin = idSignin;
        this.ogAd = ogAd;
        this.ogSoyad = ogSoyad;
        this.ogTC = ogTC;
        this.ogDogum = ogDogum;
        this.ogScan = ogScan;
    }

    public IndirmliInfor(int idSignin, String ogAd, String ogSoyad, String ogTC, String ogDogum, String ogScan) {
        this.idSignin = idSignin;
        this.ogAd = ogAd;
        this.ogSoyad = ogSoyad;
        this.ogTC = ogTC;
        this.ogDogum = ogDogum;
        this.ogScan = ogScan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSignin() {
        return idSignin;
    }

    public void setIdSignin(int idSignin) {
        this.idSignin = idSignin;
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

    public String getOgDogum() {
        return ogDogum;
    }

    public void setOgDogum(String ogDogum) {
        this.ogDogum = ogDogum;
    }

    public String getOgScan() {
        return ogScan;
    }

    public void setOgScan(String ogScan) {
        this.ogScan = ogScan;
    }
}
