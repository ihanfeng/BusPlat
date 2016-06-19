package com.zhiyin.lbs.geo;

import java.util.List;

public class GeoHash {
	
	public static String encodeHash(double latitude, double longitude) {
		return com.github.davidmoten.geo.GeoHash.encodeHash(latitude, longitude);
	}

	public static List<String> neighbours(String hash) {
		return com.github.davidmoten.geo.GeoHash.neighbours(hash);
	}
	
	public static List<String> neighbours(double latitude, double longitude) {
		return com.github.davidmoten.geo.GeoHash.neighbours(encodeHash(latitude, longitude));
	}
}
