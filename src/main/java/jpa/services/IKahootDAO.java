package jpa.services;

import jakarta.transaction.Transactional;
import jpa.domain.Kahoot;
import jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IKahootDAO extends JpaRepository<Kahoot, Long> {

}
