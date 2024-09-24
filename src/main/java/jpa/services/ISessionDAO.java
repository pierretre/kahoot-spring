package jpa.services;

import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import jpa.domain.Session;
import jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ISessionDAO extends JpaRepository<Session, Long> {

    @Query("select u from User u join u.session s where s.id = :session_id order by u.score DESC")
    List<User> listUsersScoresForSessionById(@Param("session_id") long id);
}
