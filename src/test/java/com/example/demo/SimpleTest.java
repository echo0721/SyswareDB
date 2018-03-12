package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.StringUtils;

public class SimpleTest {
	public static void main(String[] args) {
//		String demo ="/ create POIS;DF; createend  p2m3 paper create ";
//		
//		System.out.println(demo.startsWith("/ "));
//		System.out.println(demo.replace("/ ",""));
//		
//		System.out.println(demo.substring(0, demo.length()-1));
//		System.out.println(demo.substring(0, demo.lastIndexOf("end")));
//		
//		int countOccurrencesOf = StringUtils.countOccurrencesOf(demo, "create");
//		System.out.println(countOccurrencesOf);
//		
		List<String> files = new ArrayList<>();
		
		files.add("P2M.0-24710.sql");
		files.add("2_p2m.sql");
		files.add("1_base.sql");
		
		
		Collections.sort(files, new Comparator<String>() {
			@Override
			public int compare(String file1,String file2) {
				return file1.compareTo(file2);
			}
		});
		
		for (String string : files) {
			System.out.println(string);
		}
		
		
	}
}
