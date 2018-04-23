package com.jack.job;

import com.jack.intepen.dao.ElderFamilyDao;
import com.jack.intepen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 11407 on 23/023.
 */
@Component
public class SmartWatchServer {
//    //搭建服务器端
//    public static void main(String[] args) throws IOException{
//        SocketServer socketService = new SocketServer();
//        socketService.startServer();
//    }

    private Logger logger = LoggerFactory.getLogger(SmartWatchServer.class);
    public void startServer(){
        try{
            ServerSocket server = new ServerSocket(13002);
            String address = InetAddress.getLocalHost().getHostAddress().toString();
            logger.info("------------Server start success! ip: " + address + "----------");
            ExecutorService clientSocket = Executors.newCachedThreadPool();
            while (true) {
                try {
                    Socket socket = server.accept();
                    System.out.println("New client connect" + socket);

                    //线程处理socket
                    clientSocket.execute(new Task(socket));
                    //new Thread(new Task(socket)).start();

                } catch (Exception e) {
                    System.out.println("Accept Error." + e);
                }
            }

        }catch(Exception e) {
            System.out.println("Server start fail:"+e);
        }

    }

    static class Task implements Runnable {

        private Socket socket;

        //private ElderFamilyDao elderFamilyDao;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                handlerSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        private void handlerSocket() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            String line = in.readLine();
            while (!line.equals("byebye") && line != null){
                System.out.println("Client"+socket+":" + line);
//                String[] elderFamily = line.split(" ");
//                Integer elderId = Integer.parseInt(elderFamily[0]);
//                Integer familyId = Integer.parseInt(elderFamily[1]);
//                elderFamilyDao = SpringBeanUtil.getBean(ElderFamilyDao.class);
//                elderFamilyDao.insertElderFamilyRelation(elderId, familyId);
                line = in.readLine();
            }

            System.out.println("Client disconnect "+socket+":" + line);

            br.close();
            out.close();
            in.close();
            socket.close();
        }
    }
}