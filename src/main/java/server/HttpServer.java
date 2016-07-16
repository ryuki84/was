package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import util.CommUtil;

public class HttpServer {
    private static final Logger logger = Logger.getLogger(HttpServer.class.getCanonicalName());

    public static void main(String[] args) throws Exception{
    	 int port = CommUtil.getServerProt(); // port������ CommUtil ���� �����´�.
    	 try (ServerSocket serverSocekt = new ServerSocket(port)) {
             logger.info("Accepting connections on port " + serverSocekt.getLocalPort());
             
             // Ŭ���̾�Ʈ ����
             System.out.println("Wating Connect Client...");
             Socket socket;
             while ( (socket = serverSocekt.accept() ) != null ) {
                 RequestProcessor connection = new RequestProcessor(socket);
                 connection.start();
             }
         }
    }
}