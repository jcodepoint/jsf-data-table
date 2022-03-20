package com.jcodepoint.model;

import java.io.Serializable;

public class Contributor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String country;
	private Integer age;
	private Double contribution;
	
	private Boolean editable;
	
	public Contributor() {
		
	}
	
	public Contributor(Integer id, String name, Integer age, String country, Double contribution) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.country = country;
		this.contribution = contribution;
	}
	
	public Contributor(Contributor c) {
		this.id = c.getId();
		this.name = c.getName();
		this.age = c.getAge();
		this.country = c.getCountry();
		this.contribution = c.getContribution();
	}
	
	
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Double getContribution() {
		return contribution;
	}
	
	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

}
