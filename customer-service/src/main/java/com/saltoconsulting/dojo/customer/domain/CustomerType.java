package com.saltoconsulting.dojo.customer.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerType implements Serializable {
	private static final long serialVersionUID = -5667863549911589331L;

	@Id
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
			boolean sameLabel = this.label == type.label || (this.label != null && this.label.equals(type.label));
			if (!sameLabel) {
				return false;
			}
			return true;
		}
		return false;
	}
}
