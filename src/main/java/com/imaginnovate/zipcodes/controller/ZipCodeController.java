package com.imaginnovate.zipcodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.zipcodes.model.ZipCodeResponse;
import com.imaginnovate.zipcodes.service.ZipCodeService;

@RestController
@RequestMapping("/zip_codes/")
public class ZipCodeController {

	@Autowired
	private ZipCodeService zipCodeService;

	@GetMapping("hello")
	public String hello() {
		return "Hey, How are you.!";
	}

	@PostMapping("save_from_local_csv")
	public String saveZipCodes() {
		zipCodeService.save("./zipcode.csv");
		return "Saved successfully.";
	}

	@GetMapping("zip_codes_in_polygon")
	public List<ZipCodeResponse> getZipCodesInPolygon(@RequestParam(name = "polygon") String polygon) {
		return zipCodeService.getZipCodesInPolygon(polygon);
	}
}
