package com.imaginnovate.zipcodes.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.zipcodes.entity.ZipCodes;
import com.imaginnovate.zipcodes.model.LatLng;
import com.imaginnovate.zipcodes.model.ZipCodeResponse;
import com.imaginnovate.zipcodes.repository.ZipCodeRepository;
import com.imaginnovate.zipcodes.util.PolyUtil;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class ZipCodeService {

	@Autowired
	private ZipCodeRepository repository;

	@Autowired
	private PolyUtil polyUtil;

	public void save(List<ZipCodes> list) {
		if (list == null || list.size() < 0)
			return;
		repository.saveAll(list);
	}

	public void save(ZipCodes zipCode) {
		if (zipCode == null)
			return;
		repository.save(zipCode);
	}

	public void save(String path) {
		List<ZipCodes> zipCodes = fetchZipCodes(path);
		save(zipCodes);
	}

	private List<ZipCodes> fetchZipCodes(String path) {
		try {
			List<ZipCodes> zipCodes = new CsvToBeanBuilder<ZipCodes>(new FileReader("./zipcode.csv"))
					.withType(ZipCodes.class).build().parse();

			System.out.println(zipCodes.size());
			return zipCodes;

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<ZipCodeResponse> getZipCodesInPolygon(String rawData) {
		if (rawData == null || rawData.isEmpty()) {
			return null;
		} else {
			List<LatLng> polygon = getPolygon(rawData);
			if (polygon == null || polygon.size() < 0) {
				// return no data found
			} else {
				List<ZipCodeResponse> zipCodesRaw = getZipcodesRaw(polygon);
				if (zipCodesRaw == null || zipCodesRaw.size() <= 0) {
					// return no data found
				} else {

					List<ZipCodeResponse> responses = new ArrayList<>();
					for (ZipCodeResponse zipCode : zipCodesRaw) {
						LatLng point = new LatLng(zipCode.getLatitude(), zipCode.getLongitude());
						if (polyUtil.pointInPolygon(point, polygon)) {
							responses.add(zipCode);
						}
					}

					return responses;
				}
			}
		}
		return null;
	}

	protected List<ZipCodeResponse> getZipcodesRaw(List<LatLng> polygon) {
		Map<String, Double> map = polyUtil.getMinAndMaxPointsFromPolygon(polygon);
		return repository.findZipCodes(map.get("min_lat"), map.get("max_lat"), map.get("min_lng"), map.get("max_lng"));
	}

	private ZipCodeResponse getZipCodeResponse(ZipCodes zipCode) {
		ZipCodeResponse zipCodeResponse = new ZipCodeResponse();
		zipCodeResponse.setCity(zipCode.getCity());
		zipCodeResponse.setLatitude(zipCode.getLat());
		zipCodeResponse.setLongitude(zipCode.getLng());
		zipCodeResponse.setZipCode(zipCode.getZipCode());

		return zipCodeResponse;
	}

	protected List<LatLng> getPolygon(String rawData) {
		List<LatLng> polygon = new ArrayList<LatLng>();
		StringTokenizer st1 = new StringTokenizer(rawData, "|");

		while (st1.hasMoreTokens()) {
			String point = st1.nextToken();
			if (point != null && point.length() > 0) {
				point.trim();
				String[] pos = point.split(",");
				try {
					polygon.add(new LatLng(Double.parseDouble(pos[0]), Double.parseDouble(pos[1])));
				} catch (NumberFormatException nfe) {

				}
			}

		}

		return polygon;
	}
}
