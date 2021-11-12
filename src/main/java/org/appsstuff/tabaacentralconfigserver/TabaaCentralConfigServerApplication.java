package org.appsstuff.tabaacentralconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TabaaCentralConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TabaaCentralConfigServerApplication.class, args);
    }

}
