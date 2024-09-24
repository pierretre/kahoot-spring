package jpa.services;

import jakarta.transaction.Transactional;
import jpa.domain.QCMAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQCMAnswerDAO extends JpaRepository<QCMAnswer, Long> {

}
