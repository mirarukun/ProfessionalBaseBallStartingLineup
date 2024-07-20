package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Iine;


@Repository
public interface IineRepository extends JpaRepository<Iine, Integer>{
}