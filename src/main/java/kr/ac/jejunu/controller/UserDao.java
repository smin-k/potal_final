package kr.ac.jejunu.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserInfo, Integer> {
}