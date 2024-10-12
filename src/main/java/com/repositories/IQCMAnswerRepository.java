package com.repositories;

import com.domain.MultipleChoiceQuestionAnswer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQCMAnswerRepository extends JpaRepository<MultipleChoiceQuestionAnswer, Long> {

}
