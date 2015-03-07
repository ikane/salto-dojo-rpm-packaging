package com.saltoconsulting.dojo.web.json;

import java.io.Serializable;

public class CustomerTypeJSON implements Serializable {

	private static final long serialVersionUID = 4656710135261961334L;
	
	private Integer id;
	private String name;

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

}
