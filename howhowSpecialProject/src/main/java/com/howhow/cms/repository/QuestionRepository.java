package com.howhow.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
