package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import util.CommUtil;

public class RequestProcessor extends Thread {
	private final static Logger logger = Logger.getLogger(RequestProcessor.class.getCanonicalName());
	private Socket connection;
	
    public RequestProcessor(Socket connection) {
        this.connection = connection;
    }

	public void run(){
		
		try{
			OutputStream out = connection.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			byte[] bodyData = CommUtil.getHttpData(connection);
			responseBody(dos, bodyData);
			
		} catch (IOException e){
			logger.info(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
	private void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}
	
}
