package com.saltoconsulting.dojo.customer.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = -7004730395836172475L;
	
	private Integer id;
	private String name;
	private String firstName;
	private CustomerType type;
	
	@Id
	@SequenceGenerator(name="customerSeq", sequenceName="CUSTOMER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
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

	@ManyToOne(optional = false)
	@JoinColumn(name="FK_CUSTOMER_TYPE_ID")
	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Customer) {
			Customer customer = (Customer) obj;
			if (customer == this) {
				return true;
			}
			boolean sameId = this.id == customer.id || (this.id != null && this.id.equals(customer.id));
			if (!sameId) {
				return false;
			}
			boolean sameName = this.name == customer.name || (this.name != null && this.name.equals(customer.name));
			if (!sameName) {
				return false;
			}
			boolean sameFirstname = this.firstName == customer.firstName || (this.firstName != null && this.firstName.equals(customer.firstName));
			if (!sameFirstname) {
				return false;
			}
			boolean sameType = this.type == customer.type || (this.type != null && this.type.equals(customer.type));
			if (!sameType) {
				return false;
			}
			return true;
		}
		return false;
	}
}
