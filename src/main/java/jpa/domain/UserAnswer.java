package jpa.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type")
@NamedQuery(name = "UserAnswer .findAll", query = "select a from UserAnswer a")
public class UserAnswer {

    private long id;
    private Date datetime;
    private boolean isGood;
    private User user;

    public UserAnswer() {
    }

    public UserAnswer(Date datetime, boolean isGood, User user) {
        this.datetime = datetime;
        this.isGood = isGood;
        this.user = user;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
