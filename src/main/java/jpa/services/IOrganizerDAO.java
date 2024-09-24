package jpa.services;

import jpa.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrganizerDAO extends JpaRepository<Organizer, Long> {

}
