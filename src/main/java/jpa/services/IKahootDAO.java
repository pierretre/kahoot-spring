package jpa.services;

import jpa.domain.Kahoot;
import jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKahootDAO extends JpaRepository<Kahoot, Long> {

}
