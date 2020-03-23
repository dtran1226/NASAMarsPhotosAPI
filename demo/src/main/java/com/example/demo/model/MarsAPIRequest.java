package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "mars_api_request")
public class MarsAPIRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roverType;
	private int marsSol;

	@ElementCollection
	@CollectionTable(name = "camera_abbr_mapping", joinColumns = {
			@JoinColumn(name = "request_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "camera")
	@Column(name = "camera_abbr")
	private Map<String, String> cameraAbbriviations = new HashMap<String, String>() {
		{
			put("Front Hazard Avoidance Camera", "FHAZ");
			put("Rear Hazard Avoidance Camera", "RHAZ");
			put("Mast Camera", "MAST");
			put("Chemistry and Camera Complex", "CHEMCAM");
			put("Mars Hand Lens Imager", "MAHLI");
			put("Mars Descent Imager", "MARDI");
			put("Navigation Camera", "NAVCAM");
			put("Panoramic Camera", "PANCAM");
			put("Miniature Thermal Emission Spectrometer (Mini-TES)", "MINITES");
		}
	};
	@ElementCollection
	@CollectionTable(name = "mars_camera_mapping", joinColumns = {
			@JoinColumn(name = "request_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "camera")
	@Column(name = "valid_camera")
	private Map<String, Boolean> cameras = new HashMap<String, Boolean>() {
		{
			put("Front Hazard Avoidance Camera", Boolean.FALSE);
			put("Rear Hazard Avoidance Camera", Boolean.FALSE);
			put("Mast Camera", Boolean.FALSE);
			put("Chemistry and Camera Complex", Boolean.FALSE);
			put("Mars Hand Lens Imager", Boolean.FALSE);
			put("Mars Descent Imager", Boolean.FALSE);
			put("Navigation Camera", Boolean.FALSE);
			put("Panoramic Camera", Boolean.FALSE);
			put("Miniature Thermal Emission Spectrometer (Mini-TES)", Boolean.FALSE);
		}
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoverType() {
		return roverType;
	}

	public void setRoverType(String roverType) {
		this.roverType = roverType;
	}

	public int getMarsSol() {
		return marsSol;
	}

	public void setMarsSol(int marsSol) {
		this.marsSol = marsSol;
	}

	public Map<String, Boolean> getCameras() {
		return cameras;
	}

	public void setCameras(Map<String, Boolean> cameras) {
		this.cameras = cameras;
	}

	public Map<String, String> getCameraAbbriviations() {
		return cameraAbbriviations;
	}

	public void setCameraAbbriviations(Map<String, String> cameraAbbriviations) {
		this.cameraAbbriviations = cameraAbbriviations;
	}

	@Override
	public String toString() {
		return "MarsAPIRequest [roverType=" + roverType + ", marsSol=" + marsSol + ", cameraAbbriviations="
				+ cameraAbbriviations + ", cameras=" + cameras + "]";
	}
}
