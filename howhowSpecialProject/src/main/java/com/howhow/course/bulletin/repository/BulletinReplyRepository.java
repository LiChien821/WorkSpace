package com.howhow.course.bulletin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.howhow.entity.BulletinReply;

@Repository
public interface BulletinReplyRepository extends JpaRepository<BulletinReply, Integer>{
	
	@Query(nativeQuery = true,
	           value = "SELECT \n"
	           		+ "    *\n"
	           		+ "FROM\n"
	           		+ "    bulletinreply\n"
	           		+ "WHERE\n"
	           		+ "    bulletin_id IN (SELECT \n"
	           		+ "            bulletin_id\n"
	           		+ "        FROM\n"
	           		+ "            bulletin\n"
	           		+ "        WHERE\n"
	           		+ "            bulletin_id  = ?1)\n"
	           		+ "ORDER BY creation_time DESC;")
		public List<BulletinReply> findAllByBulletinId(Integer id);
}
