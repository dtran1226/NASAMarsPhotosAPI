package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsCamera {
	private long id;
	private String name;
	@JsonProperty("rover_id")
	private long roverId;
	@JsonProperty("full_name")
	private String fullName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRoverId() {
		return roverId;
	}

	public void setRoverId(long roverId) {
		this.roverId = roverId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "MarsCamera [id=" + id + ", name=" + name + ", roverId=" + roverId + ", fullName=" + fullName + "]";
	}
}
