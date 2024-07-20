package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer>{
	
	@Query(value = "SELECT * FROM players WHERE id like :id " ,nativeQuery = true)
	Player findById(@Param("id") int id);
	
}
