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
@Table(name = "starting_lineups")
@Getter
@Setter
public class StartingLineup {
	
	@Id
	@Column //カラム名の指定
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	private int id;

	@Column
	private int userId;

	@Column
	private int onePlayerId;
	
	@Column
	private int onePositionId;

	@Column
	private int twoPlayerId;
	
	@Column
	private int twoPositionId;
	
	@Column
	private int threePlayerId;
	
	@Column
	private int threePositionId;
	
	@Column
	private int fourPlayerId;
	
	@Column
	private int fourPositionId;
	
	@Column
	private int fivePlayerId;
	
	@Column
	private int fivePositionId;
	
	@Column
	private int sixPlayerId;
	
	@Column
	private int sixPositionId;
	
	@Column
	private int sevenPlayerId;
	
	@Column
	private int sevenPositionId;
	
	@Column
	private int eightPlayerId;
	
	@Column
	private int eightPositionId;
	
	@Column
	private int ninePlayerId;
	
	@Column
	private int ninePositionId;
	
	@Column
	private int starterPitcherPlayerId;
	
	@Column
	private int setUpperPitcherPlayerId;
	
	@Column
	private int closerPitcherPlayerId;

}
