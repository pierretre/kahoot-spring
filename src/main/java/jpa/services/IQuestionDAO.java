package jpa.services;

import jakarta.transaction.Transactional;
import jpa.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQuestionDAO extends JpaRepository<Question, Long> {

}
