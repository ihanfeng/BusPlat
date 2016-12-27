package de.codecentric.batch;

import lombok.extern.slf4j.Slf4j;


import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {

    @Scheduled(fixedRate = 1000)
    public void print(){
      log.info("testinfo");

        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("trace info");
        MDC.clear();
    }

	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
