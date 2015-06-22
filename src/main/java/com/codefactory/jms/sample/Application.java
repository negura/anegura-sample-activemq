package com.codefactory.jms.sample;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@EnableJms
@ImportResource("classpath:spring/application-context-jms.xml")
public class Application {

	public static void main(String[] args) {
		// Clean out any ActiveMQ data from a previous run
		FileSystemUtils.deleteRecursively(new File("activemq-data"));

		ConfigurableApplicationContext context = SpringApplication.run(
				Application.class, args);

		QueueSender queueSender = context.getBean(QueueSender.class);
		queueSender.send("textMessage1");
	}
}
