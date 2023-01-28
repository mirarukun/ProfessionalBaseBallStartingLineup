package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer>{

	@Query(value = "SELECT * FROM positions WHERE id like :id " ,nativeQuery = true)
	Position findById(@Param("id") int id);
	
}
