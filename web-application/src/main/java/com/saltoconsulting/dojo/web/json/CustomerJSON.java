package com.saltoconsulting.dojo.web.json;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CustomerJSON implements Serializable {
	
	private static final long serialVersionUID = -7004730395836172475L;

	private Integer id;
	private String name;
	private String firstName;
	private CustomerTypeJSON type;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public CustomerTypeJSON getType() {
		return type;
	}

	public void setType(CustomerTypeJSON type) {
		this.type = type;
	}

}
