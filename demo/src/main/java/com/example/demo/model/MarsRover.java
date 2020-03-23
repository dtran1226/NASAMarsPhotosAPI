package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsRover {
	private long id;
	private String name;
	@JsonProperty("landing_date")
	private LocalDate landingDate;
	@JsonProperty("launch_date")
	private LocalDate launchDate;

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

	public LocalDate getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(LocalDate landingDate) {
		this.landingDate = landingDate;
	}

	public LocalDate getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

	@Override
	public String toString() {
		return "MarsRover [id=" + id + ", name=" + name + ", landingDate=" + landingDate + ", launchDate=" + launchDate
				+ "]";
	}
}
