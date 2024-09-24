package jpa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@NamedQuery(name = "QCMAnswer.findAll", query = "select a from QCMAnswer a")
public class QCMAnswer {

    private long id;
    private QCMQuestion question;
    private String answerText;

    public QCMAnswer() {}

    public QCMAnswer(QCMQuestion question, String answerText) {
        this.question = question;
        this.answerText = answerText;
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
    @JoinColumn(name = "question_id")
    @JsonBackReference
    public QCMQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QCMQuestion question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
