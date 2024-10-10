package com.repositories;

import jakarta.transaction.Transactional;
import com.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {

}
