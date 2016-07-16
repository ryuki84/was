package util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import servlet.SimpleServletImpl;


public class CommUtil {
	private static final Logger log = Logger.getLogger(CommUtil.class.getCanonicalName());
	private static String DEFAULT_INDEX_FILE = "index.html";
	private static int port;
	private static  Properties prop = new Properties();
	
	/**
	 * INDEX_FILE 
	 * @return DEFAULT_INDEX_FILE
	 * @throws Exception
	 */
	public static String getIndexFile() throws Exception{
		prop.load(new FileInputStream("./src/webapp/WEB-INF/setting.conf"));
		DEFAULT_INDEX_FILE = prop.getProperty("INDEX_FILE");
		return DEFAULT_INDEX_FILE;
	}
	/**
	 * PORT 
	 * @return port 
	 */
	public static int getServerProt() throws Exception{
		prop.load(new FileInputStream("./src/webapp/WEB-INF/setting.conf"));
		port = Integer.parseInt(prop.getProperty("port"));
		 if (port < 0 || port > 65535){
			 port  = 8080;
		 }
		return port;
	}
	
	/**
	 * HttpData
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public static byte[] getHttpData(Socket connection) throws Exception {
		SimpleServletImpl simpleServletImpl = new SimpleServletImpl();
		byte[] bodyData = null;
		try(InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()){
			Writer writer = new OutputStreamWriter(out);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			String get = bufferReader.readLine();
			String[] token = get.split(" ");
			String method = token[0];		
			String url		 = token[1];		
			
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(url);
			
			// METHOD 가 GET방식일 경우.
			if( "GET".equals(method) ){
				// 기본URL 이거나 /favicon.ico 가 들어올 경우 기본페이지로 넘긴다.
				if("/".equals(url) || "/favicon.ico".equals(url)){
					url = CommUtil.getIndexFile();		// Default page, index.html로 이동.
					bodyData = Files.readAllBytes(new File("./src/webapp"+url).toPath());
					sendHeader(writer, "HTTP/1.0 200 OK", contentType, bodyData.length);
				}
				// url 입력이 있을 경우.
				else{
					// url 이 main일 경우
					if("/main".equals(url)){
						url = simpleServletImpl.getMainPage();	
						bodyData = Files.readAllBytes(new File("./src/webapp"+url).toPath());
						sendHeader(writer, "HTTP/1.0 200 OK", contentType, bodyData.length);
					}
					// url 이 now 경우.
					else if("/now".equals(url)){
						String now = simpleServletImpl.getNowDateTime();
						bodyData = now.getBytes();
						sendHeader(writer, "HTTP/1.0 200 OK", contentType, bodyData.length);
					}
					// 다른 값이 올 경우 해당 페이지 연결.
					else{
						File file = new File("./src/webapp"+url);
						if(!file.exists()){
							bodyData = Files.readAllBytes(new File("./src/webapp/error/404.html").toPath());
							sendHeader(writer, "HTTP/1.0 404 File Not Found", contentType, bodyData.length);
						}else{
							bodyData = Files.readAllBytes(new File("./src/webapp" + url).toPath());
							sendHeader(writer, "HTTP/1.0 200 OK", contentType, bodyData.length);
						}
					}
				}
			} else{
				bodyData = Files.readAllBytes(new File("./src/webapp/error/501.html").toPath());
				sendHeader(writer, "HTTP/1.0 501 Not Implemented", contentType, bodyData.length);
			}
			
			out.write(bodyData);
			out.flush();
			writer.flush();
			
		}catch (IOException ex){
			log.log(Level.WARNING, ex.getMessage(), ex);
		} catch (Exception e){
			e.printStackTrace();
		}
		return bodyData;
	}
	
    private static void sendHeader(Writer out, String responseCode, String contentType, int length) throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: HTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }

}
