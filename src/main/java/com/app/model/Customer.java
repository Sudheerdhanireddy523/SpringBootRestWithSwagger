package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Integer cid;
	private String cname;
	private String serviceid;
	private String mode;
	

}
