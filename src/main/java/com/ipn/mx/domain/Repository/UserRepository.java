package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreoUsuario(String correoUsuario);
}
