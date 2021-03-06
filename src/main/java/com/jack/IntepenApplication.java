package com.jack;

import com.jack.job.SmartWatchServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class IntepenApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntepenApplication.class, args);
		SmartWatchServer smartWatchServer = new SmartWatchServer();
		smartWatchServer.startServer();
	}
}
