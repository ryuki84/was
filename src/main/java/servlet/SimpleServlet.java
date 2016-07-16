package servlet;

public interface SimpleServlet {
	/*
	 * 확장 가능
	 */
	String getMainPage() throws Exception;
	String getNowDateTime() throws Exception;
}
