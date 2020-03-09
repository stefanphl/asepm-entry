package com.example.asepmentry;

import com.example.asepmentry.service.SkyScannerConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AsepmEntryApplication {

	@Autowired
	private SkyScannerConnector connector;

	public static void main(String[] args) {
		SpringApplication.run(AsepmEntryApplication.class, args);
	}

	@PostConstruct
	private void getInstances() {
		connector.getCurrencies();
	}

}
