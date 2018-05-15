package twitterfeed.core.entity;

import javax.persistence.*;
import java.util.List;



public interface Follower extends User {

    @Id
    @Column(name ="twitter_handle")
     String getTwitterHandle();

     void setTwitterHandle(String handler);

    List<TwitterUser> follows();
}
