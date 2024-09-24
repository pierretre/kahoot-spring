package jpa.services;

import jakarta.transaction.Transactional;
import jpa.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IOrganizerDAO extends JpaRepository<Organizer, Long> {

}
