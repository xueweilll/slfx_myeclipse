package com.benqzl.unit;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateCode {

	public static String CreateCodeByType(String type){
		String code="";		
		int x=(int)(Math.random()*10000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmm");  
		Date date=new Date();  
		String str=sdf.format(date);  

		code = type +"-" + str + "-" +String.format("%04d", x);;
		return code;
	}
	
	public static void main(String[] args){
		String code = CreateCodeByType("JS");
		System.out.println(code);
	}
	
}
