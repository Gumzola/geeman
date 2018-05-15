package twitterfeed.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "TWITTER_ACCOUNT")
public class TwitterAccount {
    public TwitterAccount() {
    }

    public TwitterAccount(String username, Date registrationDate, Date lastLogin) {
        this.username = username;
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
    }

    private String password;
    private String username;
    private Date registrationDate;
    private Date lastLogin;

    @Id
    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    @Column(name = "last_login")
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

}
