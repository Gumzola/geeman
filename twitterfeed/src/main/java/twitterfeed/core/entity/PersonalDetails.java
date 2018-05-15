package twitterfeed.core.entity;


import javax.persistence.*;

@Entity
@Table(name = "personal_details")
public class PersonalDetails {


    private long person_id;
    private String firstName;
    private String LastName;
    private String emailAddress;
    private String phoneNumber;

    public PersonalDetails() {
    }

    public PersonalDetails(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        LastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue
    @Column(name = "personal_details_id")
    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    @Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="last_name")
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
