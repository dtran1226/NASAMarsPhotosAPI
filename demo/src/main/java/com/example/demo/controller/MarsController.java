package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.MarsAPIRequest;
import com.example.demo.model.MarsAPIResponse;
import com.example.demo.service.MarsService;
import com.mysql.cj.util.StringUtils;

@Controller
public class MarsController {

	// Inject a singleton service to this controller to do business logic
	@Autowired
	private MarsService marsService;

	@GetMapping("/")
	public String getHomeview(ModelMap model, MarsAPIRequest marsAPIRequest) {
		// Set default Rover type to 'oppotunity' when first loaded
		if (StringUtils.isNullOrEmpty(marsAPIRequest.getRoverType())) {
			marsAPIRequest.setRoverType("opportunity");
		}
		// Call request to NASA API
		MarsAPIResponse marsPhotosResponseFromNasa = marsService.getMarsPhotosResponseFromNasa(marsAPIRequest);
		// Put all necessary variables to Model to be accessed from Client
		// side
		model.put("marsResponse", marsPhotosResponseFromNasa);
		model.put("marsAPIRequest", marsAPIRequest);
		model.put("validCameras", marsService.getValidCameras());
		// Save a request information from Client to MySQL DB
		marsService.saveRequest(marsAPIRequest);
		return "index";
	}
}