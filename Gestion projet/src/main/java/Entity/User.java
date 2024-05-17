package Entity;

public class User {
    private String username;
    private String password;

    private int id;

    private String email;
    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }

    public User(String name, String email) {
        this.username = username;
        this.email=email;
    }
    public User(int id){
        this.id=id;
    }

    public User(String username,int id) {
        this.username=username;
        this.id=id;
    }

    public User(int userId, String username, String userEmail) {
        this.id=userId;
        this.username=username;
        this.email=userEmail;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername(){return username;}

    public int getId() {
        return id;
    }
    public void setId(){
        this.id=id;
    }
}