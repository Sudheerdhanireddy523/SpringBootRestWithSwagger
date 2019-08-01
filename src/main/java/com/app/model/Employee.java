package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Integer eid;
	private String ename;
	private Double esal;
	

}
