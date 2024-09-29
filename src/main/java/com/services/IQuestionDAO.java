package com.services;

import jakarta.transaction.Transactional;
import com.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQuestionDAO extends JpaRepository<Question, Long> {

}
