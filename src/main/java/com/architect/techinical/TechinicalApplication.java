package com.architect.techinical;

import com.architect.techinical.service.socket.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TechinicalApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TechinicalApplication.class, args);

        BrokerService brokerServer = applicationContext.getBean(BrokerService.class);
        brokerServer.msgReciveAndSave();
    }

}
