package jpa.services;

import jpa.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionDAO extends JpaRepository<Session, Long> {

}
