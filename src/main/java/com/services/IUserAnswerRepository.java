package com.services;

import jakarta.transaction.Transactional;
import com.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface IUserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> deleteUserAnswerByUser_Id(@Param("user_id") long id);
}
