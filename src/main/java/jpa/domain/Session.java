package jpa.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Session {

    private long id;
    private LocalDateTime date = LocalDateTime.now();
    private Kahoot kahoot;
    private List<User> guests = new ArrayList<>();

    public Session() {}

    public Session(LocalDateTime date, Kahoot kahoot, List<User> guests) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
