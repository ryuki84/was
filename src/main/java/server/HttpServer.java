package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import util.CommUtil;

public class HttpServer {
    private static final Logger logger = Logger.getLogger(HttpServer.class.getCanonicalName());

    public static void main(String[] args) throws Exception{
    	 int port = CommUtil.getServerProt(); // port정보는 CommUtil 에서 가져온다.
    	 try (ServerSocket serverSocekt = new ServerSocket(port)) {
             logger.info("Accepting connections on port " + serverSocekt.getLocalPort());
             
             // 클라이언트 연결
             System.out.println("Wating Connect Client...");
             Socket socket;
             while ( (socket = serverSocekt.accept() ) != null ) {
                 RequestProcessor connection = new RequestProcessor(socket);
                 connection.start();
             }
         }
    }
}