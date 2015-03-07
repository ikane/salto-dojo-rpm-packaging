package com.saltoconsulting.dojo.customer.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerType implements Serializable {
	private static final long serialVersionUID = -5667863549911589331L;

	@Id
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

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof CustomerType) {
			CustomerType type = (CustomerType) obj;
			if (type == this) {
				return true;
			}
			boolean sameId = this.id == type.id || ( this.id != null && this.id.equals(type.id) );
			if (!sameId) {
				return false;
			}
			boolean sameName = this.name == type.name || (this.name != null && this.name.equals(type.name));
			if (!sameName) {
				return false;
			}
			return true;
		}
		return false;
	}
}
