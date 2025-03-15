package com.internaldb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users") 
@Data
public class Users {

	@Id
	private Integer accountId;
	private String username;
	private String password;
	
	
}
