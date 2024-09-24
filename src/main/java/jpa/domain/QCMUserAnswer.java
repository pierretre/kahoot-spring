package jpa.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DiscriminatorValue("QCMA")
public class QCMUserAnswer extends UserAnswer {
    private QCMAnswer qcmAnswer;

    public QCMUserAnswer() {
    }

    public QCMUserAnswer(QCMAnswer qcmAnswer, LocalDateTime datetime, boolean isGood, User user) {
        super(datetime, isGood, user);
        this.qcmAnswer = qcmAnswer;
    }

    @ManyToOne
    @JoinColumn(name = "qcm_answer_id")
    public QCMAnswer getQcmAnswer() {
        return qcmAnswer;
    }

    public void setQcmAnswer(QCMAnswer qcmAnswer) {
        this.qcmAnswer = qcmAnswer;
    }

}
