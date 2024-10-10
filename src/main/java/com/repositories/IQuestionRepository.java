package com.repositories;

import jakarta.transaction.Transactional;
import com.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IQuestionRepository extends JpaRepository<Question, Long> {

}
