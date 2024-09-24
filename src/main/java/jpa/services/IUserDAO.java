package jpa.services;

import jakarta.transaction.Transactional;
import jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IUserDAO extends JpaRepository<User, Long> {

}
