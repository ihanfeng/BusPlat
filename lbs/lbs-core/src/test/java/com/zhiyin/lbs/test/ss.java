package com.zhiyin.lbs.test;

import java.util.List;

import com.github.davidmoten.geo.GeoHash;

public class ss {

	public static void main(String[] args){
		String tmp = GeoHash.encodeHash(39.990355,116.316538);
		System.out.println(tmp);
		List<String> neighbours = GeoHash.neighbours(tmp);
		for(String t : neighbours){
			System.out.println(t + ", "+GeoHash.decodeHash(t));
		}
		
		System.out.println();
	}
}
