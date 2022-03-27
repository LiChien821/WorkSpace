package com.howhow.course.bulletin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.howhow.entity.Bulletin;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {
	
	@Query(nativeQuery = true,
	           value = "SELECT \n"
	           		+ "    *\n"
	           		+ "FROM\n"
	           		+ "    bulletin\n"
	           		+ "WHERE\n"
	           		+ "    lecture_id IN (SELECT \n"
	           		+ "            lectures_id\n"
	           		+ "        FROM\n"
	           		+ "            lectures\n"
	           		+ "        WHERE\n"
	           		+ "            section_id IN (SELECT \n"
	           		+ "                    section_id\n"
	           		+ "                FROM\n"
	           		+ "                    section\n"
	           		+ "                WHERE\n"
	           		+ "                    course_id = ?1))\n"
	           		+ "ORDER BY creation_time DESC;")
		public List<Bulletin> findAllByCourseId(Integer id);
	
}