package Model;

public class SingUpInfor {
    private int Id;
    private String UserName;
    private String Email;
    private String Pass;
    private String PassCon;



    public SingUpInfor(String userName, String email, String pass, String passCon) {
        UserName = userName;
        Email = email;
        Pass = pass;
        PassCon = passCon;
    }

    public SingUpInfor(int id, String userName, String email, String pass, String passCon) {
        Id = id;
        UserName = userName;
        Email = email;
        Pass = pass;
        PassCon = passCon;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getPassCon() {
        return PassCon;
    }

    public void setPassCon(String passCon) {
        PassCon = passCon;
    }
}
