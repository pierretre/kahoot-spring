package com.repositories;

import com.domain.Organizer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IOrganizerRepository extends JpaRepository<Organizer, Long> {

}
