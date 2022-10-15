package Model;

public class BankInfor {
    private int id;
    private int idSignin;
    private String kartNum;
    private String sonKullanma;
    private String CVV;
    private String kartIsmi;

    public BankInfor(int idSignin, String kartNum, String sonKullanma, String CVV, String kartIsmi) {
        this.idSignin = idSignin;
        this.kartNum = kartNum;
        this.sonKullanma = sonKullanma;
        this.CVV = CVV;
        this.kartIsmi = kartIsmi;
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

    public String getKartNum() {
        return kartNum;
    }

    public void setKartNum(String kartNum) {
        this.kartNum = kartNum;
    }

    public String getSonKullanma() {
        return sonKullanma;
    }

    public void setSonKullanma(String sonKullanma) {
        this.sonKullanma = sonKullanma;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getKartIsmi() {
        return kartIsmi;
    }

    public void setKartIsmi(String kartIsmi) {
        this.kartIsmi = kartIsmi;
    }
}
