package com.howhow.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.howhow.entity.Bulletin;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {

}