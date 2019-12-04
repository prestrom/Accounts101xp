public class Account {
    private String nickname;
    private String password;
    private String email;
Account(String nickname, String password, String email) {
    this.nickname = nickname;
    this.password = password;
    this.email = email;
}
    public void setNickname (String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }


}
