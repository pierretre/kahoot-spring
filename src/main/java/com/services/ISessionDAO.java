package com.services;

import jakarta.transaction.Transactional;
import com.domain.Session;
import com.domain.User;
import com.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ISessionDAO extends JpaRepository<Session, Long> {

    @Query("select u from User u join u.session s where s.id = :session_id order by u.score DESC")
    List<User> listUsersScoresForSessionById(@Param("session_id") long id);

    @Query("select ua from UserAnswer ua join ua.user u join u.session s where s.id = :session_id")
    List<UserAnswer> getUserAnswersForSessionById(@Param("session_id") long id);
}
