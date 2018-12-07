package com.app.myproject.util;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class ComboGenerator {
	public String getQuarterDropDown(String selected) {
		StringBuilder str = new StringBuilder("<select id=\"quarter\" name=\"quarter\" class=\"form-control input-sm\">");
		str.append("<option value=\"-1\">Select Quarter</option>");
		for(int i=1; i<5; i++){
			if(null != selected && selected.equals(i)){
				str.append("<option selected value=\""+i+"\">"+i+"</option>");		
			}else {
				str.append("<option value=\""+i+"\">"+i+"</option>");
			}
		}
		str.append("</select>");
		return str.toString();
	}
	
	public String getMonthDropDown(String selected) {
		StringBuilder str = new StringBuilder("<select id=\"month\" name=\"month\" class=\"form-control input-sm\">");
		str.append("<option value=\"-1\">Select Month</option>");
		for(Month month : Month.values()){
			if(null != selected && selected.equals(month.getValue())){
				str.append("<option selected value=\""+month.getValue()+"\">"+month.getDisplayName(TextStyle.FULL, Locale.getDefault())+"</option>");		
			}else {
				str.append("<option value=\""+month.getValue()+"\">"+month.getDisplayName(TextStyle.FULL, Locale.getDefault())+"</option>");
			}
		}
		str.append("</select>");
		return str.toString();
	}
	
	public String getYearDropDown(String selected) {
		StringBuilder str = new StringBuilder("<select id=\"year\" name=\"year\" class=\"form-control input-sm\">");
		str.append("<option value=\"-1\">Select Year</option>");
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for(int i=currentYear;i>(currentYear-5);i--){
			if(null != selected && Integer.parseInt(selected) == i){
				str.append("<option selected value=\""+i+"\">"+i+"</option>");		
			}else {
				str.append("<option value=\""+i+"\">"+i+"</option>");
			}
		}
		str.append("</select>");
		return str.toString();
	}
}
