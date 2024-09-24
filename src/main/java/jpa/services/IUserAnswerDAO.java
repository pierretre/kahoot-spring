package jpa.services;

import jpa.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAnswerDAO extends JpaRepository<UserAnswer, Long> {

}
