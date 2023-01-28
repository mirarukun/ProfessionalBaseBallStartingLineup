package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query(value = "SELECT * FROM users WHERE email like :email " ,nativeQuery = true)
	User findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM users WHERE id like :id " ,nativeQuery = true)
	User findById(@Param("id") int id);
	
}