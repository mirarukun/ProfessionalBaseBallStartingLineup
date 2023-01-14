package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position {

	@Id
	@Column //カラム名の指定
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	private int id;

	@Column
	private String position;

}
