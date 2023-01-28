package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {
	
	@Id
	@Column //カラム名の指定
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	private int id;

	@Column
	private String name;

	@Column
	private String throwingStrongArm;
	
	@Column
	private String battingStrongArm;

	@Column
	private String mainPosition;
	
	@Column(insertable=false, updatable=false)
	@Transient
	private String[] arraySubPosition;
	
	@Column
	private String subPosition;
}
