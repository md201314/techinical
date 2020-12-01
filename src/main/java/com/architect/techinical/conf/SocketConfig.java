package com.architect.techinical.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class SocketConfig {
    @Value("${myspring.socket.port}")
    private int SERVICE_PORT;

    @Bean
    public ServerSocket getSocket() throws IOException {
        return new ServerSocket(SERVICE_PORT);
    }
}
