package com.howhow.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.PurchasedCourse;

public interface PurchaseRepository extends JpaRepository<PurchasedCourse, Integer> {

}
