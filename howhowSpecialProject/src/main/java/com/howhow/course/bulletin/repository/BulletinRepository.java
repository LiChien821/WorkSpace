package com.howhow.course.bulletin.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.howhow.entity.Bulletin;
import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

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
	
	@Query(nativeQuery = true,
	           value = "SELECT creator_id\n"
	           		+ "FROM CourseBasic\n"
	           		+ "WHERE course_id = ?1")
	public Integer findCreatorIdByCourseId(Integer id);
	
	@Query(nativeQuery = true,
	           value = "SELECT \n"
	           		+ "    *\n"
	           		+ "FROM\n"
	           		+ "    bulletin\n"
	           		+ "WHERE\n"
	           		+ "    lecture_id  = ?1 "
	           		+ "ORDER BY creation_time DESC;")
	public List<Bulletin> findAllByLectureId(Integer id);
	
	@Query(nativeQuery = true,
	           value = "SELECT * FROM bulletin\n"
	           		+ "	WHERE bulletin_id IN (\n"
	           		+ "		SELECT bulletin_id FROM Bulletin\n"
	           		+ "		WHERE content LIKE ?1 OR title LIKE ?1 \n"
	           		+ "	    UNION \n"
	           		+ "	    SELECT bulletin_id FROM BulletinReply \n"
	           		+ "	    WHERE reply_content LIKE ?1)\n"
	           		+ "	ORDER BY creation_time DESC;")
	public List<Bulletin> findAllBySearch(String str);
	
//	@Query(nativeQuery = true,
//	           value = "SELECT B.section_id, section_name, lectures_id, lectures_name FROM lectures AS A \n"
//	           		+ "	LEFT JOIN (\n"
//	           		+ "		SELECT section_id, section_name FROM section \n"
//	           		+ "		WHERE course_id = ?1\n"
//	           		+ "	    ) AS B ON A.section_id = B.section_id\n"
//	           		+ "	WHERE A.section_id IS NOT NULL AND B.section_id IS NOT NULL\n"
//	           		+ "	ORDER BY B.section_id, A.lectures_id;")
//	public List<Object> findSectionInfoByCourseId(Integer id);

	
}