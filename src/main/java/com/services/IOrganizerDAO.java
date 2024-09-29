package com.services;

import com.domain.Organizer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IOrganizerDAO extends JpaRepository<Organizer, Long> {

}
