package com.codefactory.jms.sample;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.FileSystemUtils;

public class Receiver {

	@Autowired
	public ConfigurableApplicationContext context;

	@JmsListener(destination = "mailbox-destination", containerFactory = "myJmsConnectionFactory")
	public void receiveMessage(String message) {
		System.out.println("Received: <" + message + ">");
		context.close();
		FileSystemUtils.deleteRecursively(new File("activemq-data"));
	}
}
