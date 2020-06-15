package com.ada.cursos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {
		
		public static String PATTERN_D2_M2_Y4 = "yyyy-MM-dd";
		public static String PATTERN_D2_M2_Y4_H_m2 = "dd/MM/yyyy H:mm";

		public static String formatSdf(String pattern, Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		
		public static Date formatParse(String pattern, String fecha) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(fecha);
		}
		
		public static Date currentDate() {
			long ahoraLong = System.currentTimeMillis();
			Date date = new Date();
			date.setTime(ahoraLong);
			return date;
		}

}
