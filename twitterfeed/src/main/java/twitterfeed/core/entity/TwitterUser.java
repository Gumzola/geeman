package twitterfeed.core.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="twitter_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TwitterUser implements Follower {

    private long userNumber;

    private PersonalDetails personalDetails;
    private Address address;
    private String twitterHandle;
    private Set<TwitterUser> followers;
    private Set<Twitt> twitts;

    public TwitterUser() {
    }

    public TwitterUser(long userNumber, PersonalDetails personalDetails, Address address,
                       String twitterHandle, Set<TwitterUser> followers, Set<Twitt> twitts) {
        super();
        this.userNumber = userNumber;
        this.personalDetails = personalDetails;
        this.address = address;
        this.twitterHandle = twitterHandle;
        if(followers!=null) {
            this.followers = followers;
        }else{
            this.followers =new HashSet<TwitterUser>();
        }
        if(twitts!=null) {
            this.twitts = twitts;
        }else{
            this.twitts = new HashSet<Twitt>();
        }
    }



    @GeneratedValue
    @Column(name = "user_number")
    public long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(long userNumber) {
        this.userNumber = userNumber;
    }


    @Id
    @Column(name ="twitter_handle")
    public String getTwitterHandle() {
        return twitterHandle;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_details_id")
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }


    @ManyToMany(cascade={PERSIST,CascadeType.MERGE}, fetch= EAGER)
    @JoinTable(name ="follower" ,
                       joinColumns = @JoinColumn (name = "followed") ,inverseJoinColumns = @JoinColumn(name = "follower_handle")
                       )

    public Set<TwitterUser> getFollowers() {
        return followers;
    }



   @OneToMany(mappedBy = "user")
       public Set<Twitt> getTwitts() {

        return twitts;
    }


    public boolean addTwitt(Twitt twitt){

        twitts.add(twitt);

        return true;

    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTwitterHandle(String twitterHandle) {

        if(twitterHandle!=null)
            twitterHandle = twitterHandle.toLowerCase();
        this.twitterHandle = twitterHandle;
    }

    public void setFollowers(Set<TwitterUser> followers) {
        this.followers = followers;
    }


    public void setTwitts(Set<Twitt> twitts) {
        this.twitts = twitts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterUser that = (TwitterUser) o;

        return twitterHandle.equals(that.twitterHandle);
    }

    @Override
    public int hashCode() {
        int result = 15;
        result = 31 * result + twitterHandle.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TwitterUser{" +
                "personalDetails=" + personalDetails.getFirstName() + " "+personalDetails.getFirstName()+
                ", twitterHandle='" + twitterHandle + '\'' +
                '}';
    }

    public List<TwitterUser> follows() {
        // TODO Auto-generated method stub
        return null;
    }

    public void addFollower(TwitterUser user) {

        followers.add(user);
    }
}
