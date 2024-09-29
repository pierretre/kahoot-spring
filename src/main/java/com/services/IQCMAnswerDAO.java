package com.services;

import jakarta.transaction.Transactional;
import com.domain.MultipleChoiceQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQCMAnswerDAO extends JpaRepository<MultipleChoiceQuestionAnswer, Long> {

}
