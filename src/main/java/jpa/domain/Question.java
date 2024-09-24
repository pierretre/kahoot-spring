package jpa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
@NamedQuery(name = "Question.findAll", query = "select q from Question q")
public  abstract class Question {

    private long id;
    private String question;
    private Kahoot kahoot;

    protected Question(){}

    protected Question(String question) {
        this.question = question;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = "kahoot_id")
    @JsonBackReference
    public Kahoot getKahoot() {
        return kahoot;
    }

    public void setKahoot(Kahoot kahoot) {
        this.kahoot = kahoot;
    }
}
