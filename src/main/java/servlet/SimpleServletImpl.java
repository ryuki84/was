package servlet;

import java.util.Calendar;
import java.util.Locale;

import servlet.SimpleServlet;

public class SimpleServletImpl implements SimpleServlet{
	
	@Override
	public String getMainPage() throws Exception{
		String httpUrl = "/main.html";
		return httpUrl;
	}
	
	
	@Override
	public String getNowDateTime() throws Exception{
		 Calendar now = Calendar.getInstance(Locale.KOREA);
		 String nowDateTime = "���� �ð� :" 
									 	+ now.get ( Calendar.YEAR ) 				+ "�� " 
									 	+ (now.get ( Calendar.MONTH ) + 1) 	+ "�� " 
									 	+ now.get ( Calendar.DATE ) 				+ "�� " 
									 	+ now.get ( Calendar.HOUR_OF_DAY )  + "�� " 
									 	+ now.get ( Calendar.MINUTE ) 			+ "�� " 
									 	+ now.get ( Calendar.SECOND ) 			+ "�� ";
		 return nowDateTime;
	}
	
}
