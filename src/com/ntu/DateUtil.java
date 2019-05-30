package com.ntu;


import com.ntu.controller.AddCarDialogController;
import com.ntu.controller.AddManufacturerDialogController;
import com.ntu.domain.Dealership;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static java.sql.Date convertStringIntoSqlDate(String dateIn) {
		try {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");          
		
		   java.util.Date dateInUtil = (java.util.Date) format.parse(dateIn);		
           java.sql.Date dateOut = new java.sql.Date(dateInUtil.getTime());
           
           return dateOut;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toDateTime(Date date) {
		java.text.SimpleDateFormat sdf =
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	public static Timestamp getCurrentTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
}
