package com.gamerSync.gamerSync.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamerSync.gamerSync.models.Users;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Transactional(readOnly = true)
    Users findByUsername(String username);


}
