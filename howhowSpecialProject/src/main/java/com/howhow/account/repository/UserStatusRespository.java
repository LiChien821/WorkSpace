package com.howhow.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.UserStatus;

public interface UserStatusRespository extends JpaRepository<UserStatus, Integer> {

}
