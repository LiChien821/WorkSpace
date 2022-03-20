package com.howhow.course.common;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.UserAccountMt;

public interface CommonAccountRepository extends PagingAndSortingRepository<UserAccountMt, Integer> {

}
