package com.example.demo.model;

import java.util.List;

public class MarsAPIResponse {
	private List<MarsPhoto> photos;

	public List<MarsPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<MarsPhoto> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "MarsAPIResponse [photos=" + photos + "]";
	}
}
