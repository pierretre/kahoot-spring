package jpa.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Kahoot {

    private long id;
    private Organizer creator;
    private List<Question> questions = new ArrayList<>();

    public Kahoot() {}

    public Kahoot(Organizer creator, List<Question> questions) {
        this.creator = creator;
        this.questions = questions;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id")
    public Organizer getCreator() {
        return creator;
    }

    public void setCreator(Organizer creator) {
        this.creator = creator;
    }

    @OneToMany(mappedBy = "kahoot")
    @JsonManagedReference
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
