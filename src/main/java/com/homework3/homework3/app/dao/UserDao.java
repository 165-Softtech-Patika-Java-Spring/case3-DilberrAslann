package com.homework3.homework3.app.dao;

import com.homework3.homework3.app.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByName(String name);

   // AppUser findAllByUserId(Long userId);
}
