package com.project.SmartLeave.Repository;

import com.project.SmartLeave.Entity.Role;
import com.project.SmartLeave.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    long countByRole(Role role);
}
