package com.howhow.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.AccountRepository;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.OrderDt;
import com.howhow.entity.OrderMt;
import com.howhow.entity.PurchasedCourse;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.shopping.repository.CourseBasicRepository;
import com.howhow.shopping.repository.OrderDtRepository;
import com.howhow.shopping.repository.OrderMtRepository;
import com.howhow.student.exception.OrderStatusErrorException;
import com.howhow.student.exception.PurchasedCourseNotFoundException;
import com.howhow.student.repository.PurchasedCourseRepository;
import com.howhow.util.UtilityTool;

@Service
public class PurchasedCourseService {

	@Autowired
	PurchasedCourseRepository repo;

	@Autowired
	OrderDtRepository odtrepo;

	@Autowired
	CourseBasicRepository crepo;

	@Autowired
	AccountRepository accrepo;

	@Autowired
	OrderMtRepository omtrepo;

	public PurchasedCourse findByID(int id) {
		Optional<PurchasedCourse> bean = repo.findById(id);
		if (bean.isEmpty())
			return null;
		return bean.get();
	}

	public List<PurchasedCourse> findByUserID(int id) {
		List<PurchasedCourse> list = repo.findByUserID(id);
		return list;
	}

	public boolean deleteByID(int id) throws PurchasedCourseNotFoundException {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new PurchasedCourseNotFoundException();
		}
	}

	public boolean findPurchasedStatus(int id, int courseid) {

		if (repo.findPurchasedStatus(id, courseid) != null)
			return true;
		return false;
	}

	public List<PurchasedCourse> insertPurchasedCourse(int orderid) throws OrderNotFoundException, OrderStatusErrorException {

		try {
			OrderMt omt = omtrepo.findById(orderid).get();
			
			if(omt.getOrderStatusTypeID()!=1) throw new OrderStatusErrorException();
			
			List<OrderDt> odt = odtrepo.findByOrderID(orderid);
			UserAccountMt user = omt.getUserID();

			List<PurchasedCourse> list = new ArrayList<PurchasedCourse>();

			for (OrderDt orderDt : odt) {
				int courseID = orderDt.getCourseID().getCourseID();
				CourseBasic course = crepo.findById(courseID).get();

				PurchasedCourse pc = new PurchasedCourse(UtilityTool.getSysTime(), course, user);
				PurchasedCourse save = repo.save(pc);
				list.add(save);
			}
			return list;
		} catch (Exception e) {
			throw new OrderNotFoundException();
		}
	}

	public List<PurchasedCourse> findPurchasedCourseByCourseID(int courseid) {
		
		List<PurchasedCourse> list = repo.findByCourseID(courseid);
		return list;
	}
	
	public Integer findStudentCount(int courseid) throws CourseNotFoundException{
		try {
			Integer count = repo.findStudentCount(courseid);
			return count;
		} catch (Exception e) {
			throw new CourseNotFoundException();
		}
	}

}
