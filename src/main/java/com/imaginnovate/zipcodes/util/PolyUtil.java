package com.imaginnovate.zipcodes.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imaginnovate.zipcodes.model.LatLng;

@Component
public class PolyUtil {

	public boolean pointInPolygon(LatLng point, List<LatLng> polygon) {

		int c = 0;
		LatLng p1 = polygon.get(0);
		int n = polygon.size();

		for (int i = 1; i <= n; i++) {
			LatLng p2 = polygon.get(i % n);
			if (point.getLongitude() > Math.min(p1.getLongitude(), p2.getLongitude())
					&& point.getLongitude() <= Math.max(p1.getLongitude(), p2.getLongitude())
					&& point.getLatitude() <= Math.max(p1.getLatitude(), p2.getLatitude())
					&& p1.getLatitude() != p2.getLongitude()) {

				double xinters = (point.getLongitude() - p1.getLongitude()) * (p2.getLatitude() - p1.getLatitude())
						/ (p2.getLongitude() - p1.getLongitude()) + p1.getLatitude();
				if (p1.getLatitude() == p2.getLatitude() || point.getLatitude() <= xinters) {
					c++;
				}
			}
			p1 = p2;
		}

		return c % 2 != 0;
	}
	
	public Map<String, Double> getMinAndMaxPointsFromPolygon(List<LatLng> polygon) {
		double latmin = 85;
		double latmax = -85;
		double lngmin = 180;
		double lngmax = -180;

		for (LatLng latLng : polygon) {
			if (latmin > latLng.getLatitude())
				latmin = latLng.getLatitude();
			if (latmax < latLng.getLatitude())
				latmax = latLng.getLatitude();
			if (lngmin > latLng.getLongitude())
				lngmin = latLng.getLongitude();
			if (lngmax < latLng.getLongitude())
				lngmax = latLng.getLongitude();
		}

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("min_lat", latmin);
		map.put("max_lat", latmax);
		map.put("min_lng", lngmin);
		map.put("max_lng", lngmax);

		return map;
	}
}
