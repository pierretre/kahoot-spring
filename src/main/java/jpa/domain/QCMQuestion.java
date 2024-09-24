package jpa.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("QCMQ")
public class QCMQuestion extends Question {

    private List<QCMAnswer> possibleAnswers =  new ArrayList<>();
    private QCMAnswer goodAnswer;

    public QCMQuestion() {}

    public QCMQuestion(String question, List<QCMAnswer> possibleAnswers, QCMAnswer goodAnswer) {
        super(question);
        this.possibleAnswers = possibleAnswers;
        this.goodAnswer = goodAnswer;
    }

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    public List<QCMAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<QCMAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    @ManyToOne
    @JoinColumn(name = "good_answer_id")
    public QCMAnswer getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(QCMAnswer goodAnswer) {
        this.goodAnswer = goodAnswer;
    }
}