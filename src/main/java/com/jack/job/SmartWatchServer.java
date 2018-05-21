package com.jack.job;

import com.jack.intepen.dao.*;
import com.jack.intepen.entity.Behavior;
import com.jack.intepen.entity.Events;
import com.jack.intepen.entity.WiFiToken;
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
import java.text.SimpleDateFormat;
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

        private Logger logger = LoggerFactory.getLogger(this.getClass());
        private Socket socket;

        private BehaviorDao behaviorDao = SpringBeanUtil.getBean(BehaviorDao.class);
        private ElderDeviceDao elderDeviceDao = SpringBeanUtil.getBean(ElderDeviceDao.class);
        private WiFiTokenDao wiFiTokenDao = SpringBeanUtil.getBean(WiFiTokenDao.class);
        private EventsDao eventsDao = SpringBeanUtil.getBean(EventsDao.class);

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

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //上次存入行为表的时间
            long lasttime = 0;

            String line = in.readLine();
            while (line != null && !line.equals("byebye")){
                System.out.println("Client"+socket+":" + line);

                logger.info("-----------line:{}------------", line);
                //分隔得到的信息
                String[] datas = line.split("\\|");

                if(datas.length == 4){
                    String action = "";

                    //获取行为
                    if(datas[0] != null && !datas[0].equals("")){
                        action = datas[0];

                        if(action.contains("向下一次")){
                            action = "跌倒";
                        }
                        if(action.contains("前手臂抬臂一次至嘴边")){
                            action = "吃饭";
                        }
                    }

                    //设备ID
                    String deviceId = datas[2];

                    //将得到的时间戳转换为long型整数
                    long timestamp = Long.parseLong(datas[3]);

                    //将字符串时间戳转换为时间戳格式
                    java.sql.Timestamp datetime = new java.sql.Timestamp(timestamp*1000l);

                    //上次存入行为表到现在的间隔
                    float interval = (float)(timestamp*1000l - lasttime)/(1000*60.0f);
                    logger.info("---------interval:{}--------------", interval);
                    logger.info("-------------action:{}-----------", action);
                    if(interval > 30.0 && !action.equals("")){
                        //通过设备关联表获得老人的ID
                        Integer elderId = elderDeviceDao.queryElderIdByDeviceId(deviceId);

                        logger.info("------------elderId:{}-----------", elderId);
                        if(elderId != null){
                            Behavior behavior = new Behavior();
                            behavior.setTime(datetime.toString());
                            behavior.setValue(action);
                            behavior.setElderId(elderId);
                            logger.info("-----------behavior:{}-----------", behavior);
                            behaviorDao.insertBehavior(behavior);

                            lasttime = System.currentTimeMillis();
                        }
                        else{
                            logger.info("-------------No this Elder------------");
                        }
                    }
                    String[] wifiList = datas[1].split(",");
                    logger.info("----------wifiList:{}------------", wifiList);
                    for(String wifi : wifiList){
                        WiFiToken wiFiToken = new WiFiToken();
                        wiFiToken.setDeviceId(deviceId);
                        wiFiToken.setWifiToken(wifi);
                        wiFiToken.setTimestamp(datetime);

                        wiFiTokenDao.insertWiFiToken(wiFiToken);
                    }

                    if(action.equals("跌倒")){
                        Events events = new Events();
                        events.setEventName(action);
                        events.setTimestamp(datetime.toString());
                        events.setPriority(9);
                        events.setStatus(0);
                        eventsDao.insertEvent(events);
                    }
                }
                line = in.readLine();
            }

            if(line != null) {
                System.out.println("Client disconnect " + socket + ":" + line);
            }

            br.close();
            out.close();
            in.close();
            socket.close();
        }
    }
}