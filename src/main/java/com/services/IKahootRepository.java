package com.services;

import com.domain.Kahoot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IKahootRepository extends JpaRepository<Kahoot, Long> {

}
