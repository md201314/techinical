package com.architect.techinical.service.socket;

import com.architect.techinical.dao.Model.Attendance;
import com.architect.techinical.dao.Repository.AttendanceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

@Service
public class BrokerService {
    private Logger logger = Logger.getLogger(this.getClass());

    private ExecutorService threadPool = new ThreadPoolExecutor(10, 50, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

    @Autowired
    private ServerSocket server;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void msgReciveAndSave() throws IOException {
        while (true) {
            Socket socket = server.accept();
            logger.info(socket.toString()+"连接已建立！");
            Runnable runnable = () -> {
                try {
                    byte[] bytes = new byte[1024];
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
                        if (sb.toString().contains("\n")) {
                            String userId = sb.toString().replaceAll("\n","").replaceAll("\r","");
                            Attendance attendance = new Attendance();
                            attendance.setUserId(userId);
                            attendanceRepository.saveAndFlush(attendance);
                            sb.delete(0, sb.length());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                        if (socket.isClosed())
                            logger.info(socket.toString()+"连接已断开！");
                        else
                            logger.info(socket.toString()+"连接未断开！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.submit(runnable);
        }
    }
}
