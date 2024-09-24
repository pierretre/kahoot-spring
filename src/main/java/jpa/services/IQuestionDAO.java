package jpa.services;

import jpa.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionDAO extends JpaRepository<Question, Long> {

}
