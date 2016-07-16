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
		 String nowDateTime = "현재 시각 :" 
									 	+ now.get ( Calendar.YEAR ) 				+ "년 " 
									 	+ (now.get ( Calendar.MONTH ) + 1) 	+ "월 " 
									 	+ now.get ( Calendar.DATE ) 				+ "일 " 
									 	+ now.get ( Calendar.HOUR_OF_DAY )  + "시 " 
									 	+ now.get ( Calendar.MINUTE ) 			+ "분 " 
									 	+ now.get ( Calendar.SECOND ) 			+ "초 ";
		 return nowDateTime;
	}
	
}
