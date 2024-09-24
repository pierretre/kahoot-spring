package jpa.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Session.findAll", query = "select s from Session s")
@NamedQuery(name = "Session.usersOrderedByScore", query = "select u from User u join u.session s where s.id = :session_id order by u.score DESC")
public class Session {

    private long id;
    private Date date = new Date();
    private Kahoot kahoot;
    private List<User> guests = new ArrayList<>();

    public Session() {}

    public Session(Date date, Kahoot kahoot, List<User> guests) {
        this.date = date;
        this.kahoot = kahoot;
        this.guests = guests;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "kahoot_id")
    public Kahoot getKahoot() {
        return kahoot;
    }

    public void setKahoot(Kahoot kahoot) {
        this.kahoot = kahoot;
    }

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<User> getGuests() {
        return guests;
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }
}
