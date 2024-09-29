package com.services;

import jakarta.transaction.Transactional;
import com.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IUserAnswerDAO extends JpaRepository<UserAnswer, Long> {

}
