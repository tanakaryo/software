package com.example.ms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
