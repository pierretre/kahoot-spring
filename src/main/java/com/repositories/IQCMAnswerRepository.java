package com.repositories;

import jakarta.transaction.Transactional;
import com.domain.MultipleChoiceQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQCMAnswerRepository extends JpaRepository<MultipleChoiceQuestionAnswer, Long> {

}
