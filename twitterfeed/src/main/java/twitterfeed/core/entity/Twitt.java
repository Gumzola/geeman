package twitterfeed.core.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name ="twitt")
public class Twitt {

    private String twitterNumber;
    private TwitterUser user;
    private String twitterText;
    private Date twittTimeStamp;

    public Twitt() {
    }

    public Twitt(String twitterNumber, TwitterUser user, String twitterText,
                 Date twittTimeStamp) {
        super();
        this.twitterNumber = twitterNumber;
        this.user = user;
        this.twitterText = twitterText;
        this.twittTimeStamp = twittTimeStamp;
    }

    @Id
    @Column(name = "twitter_number")
    public String getTwitterNumber() {
        return twitterNumber;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="twitt_handle")
    public TwitterUser getUser() {
        return user;
    }


    @Column(name = "twitter_text")
    public String getTwitterText() {
        return twitterText;
    }

    @Column (name = "twitt_time_stamp")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Date getTwittTimeStamp() {
        return twittTimeStamp;
    }

    public Twitt(TwitterUser user, String twitterText) {
        super();
        this.user = user;
        this.twitterText = twitterText;
    }

    public void setTwitterNumber(String twitterNumber) {
        this.twitterNumber = twitterNumber;
    }

    public void setUser(TwitterUser user) {
        this.user = user;
    }

    public void setTwitterText(String twitterText) {
        this.twitterText = twitterText;
    }

    public void setTwittTimeStamp(Date twittTimeStamp) {
        this.twittTimeStamp = twittTimeStamp;
    }
}
