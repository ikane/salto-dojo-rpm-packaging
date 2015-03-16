package com.saltoconsulting.dojo.web.json;

import java.io.Serializable;

public class CustomerTypeJSON implements Serializable {

	private static final long serialVersionUID = 4656710135261961334L;
	
	private Integer id;
	private String label;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
