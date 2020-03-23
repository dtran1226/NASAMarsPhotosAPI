package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.MarsAPIRequest;
import com.example.demo.model.MarsAPIResponse;
import com.example.demo.model.MarsPhoto;
import com.example.demo.repository.RequestRepository;

@Service
public class MarsService {
	// Inject a singleton Repository for DB query processing in service
	@Autowired
	RequestRepository requestRepository;

	// Define a list of valid cameras by specific Rover type
	private Map<String, List<String>> validCameras = new HashMap<String, List<String>>() {
		{
			put("opportunity", Arrays.asList("Front Hazard Avoidance Camera", "Rear Hazard Avoidance Camera",
					"Navigation Camera", "Panoramic Camera", "Miniature Thermal Emission Spectrometer (Mini-TES)"));
			put("curiosity",
					Arrays.asList("Front Hazard Avoidance Camera", "Rear Hazard Avoidance Camera", "Mast Camera",
							"Chemistry and Camera Complex", "Mars Hand Lens Imager", "Mars Descent Imager",
							"Navigation Camera"));
			put("spirit", Arrays.asList("Front Hazard Avoidance Camera", "Rear Hazard Avoidance Camera",
					"Navigation Camera", "Panoramic Camera", "Miniature Thermal Emission Spectrometer (Mini-TES)"));
		}
	};

	/*
	 * Get a response of Mars photos from NASA API
	 */
	public MarsAPIResponse getMarsPhotosResponseFromNasa(MarsAPIRequest marsAPIRequest) {
		RestTemplate rt = new RestTemplate();
		List<MarsPhoto> photos = new ArrayList<>();
		MarsAPIResponse marsAPIResponse = new MarsAPIResponse();
		// Calling multiple requests to NASA API for different urls due to
		// different selected cameras. Then, add all response's photos.
		getRequestUrls(marsAPIRequest).forEach(url -> {
			MarsAPIResponse response = rt.getForObject(url, MarsAPIResponse.class);
			photos.addAll(response.getPhotos());
		});
		marsAPIResponse.setPhotos(photos);
		return marsAPIResponse;
	}

	/*
	 * Get all request urls to call based on different selections from
	 * user
	 */
	private List<String> getRequestUrls(MarsAPIRequest marsAPIRequest) {
		List<String> validCamsByRover = getValidCameras().get(marsAPIRequest.getRoverType());
		// Get all cameras which are chosen by user via checkboxes
		Map<String, Boolean> checkedCameras = marsAPIRequest.getCameras().entrySet().stream()
				.filter(camera -> Boolean.TRUE.equals(camera.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		List<String> requestUrls = new ArrayList<String>();
		// Check if the selected cameras are valid for the current chosen
		// Rover type. If so, form suitable urls for those selected cameras.
		// Unless, uncheck them accordingly.
		for (String key : checkedCameras.keySet()) {
			if (!validCamsByRover.contains(key)) {
				checkedCameras.put(key, Boolean.FALSE);
				marsAPIRequest.getCameras().put(key, Boolean.FALSE);
			} else {
				requestUrls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + marsAPIRequest.getRoverType()
						+ "/photos?sol=" + marsAPIRequest.getMarsSol() + "&camera="
						+ marsAPIRequest.getCameraAbbriviations().get(key)
						+ "&api_key=PgM8hl4Hq0d6hvhPLwZf6aG5huAw2xPX7ER1hbyd");
			}
		}
		return requestUrls;
	}

	/*
	 * Save a request information from Client to MySQL DB
	 */
	public void saveRequest(MarsAPIRequest marsAPIRequest) {
		requestRepository.save(marsAPIRequest);
	}

	public Map<String, List<String>> getValidCameras() {
		return validCameras;
	}

	public void setValidCameras(Map<String, List<String>> validCameras) {
		this.validCameras = validCameras;
	}
}