package com.example.asepmentry;

import com.example.asepmentry.modell.Request;
import com.example.asepmentry.modell.Solution;
import com.example.asepmentry.repository.RequestRepository;
import com.example.asepmentry.service.SkyScannerConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AsepmEntryApplication {

	@Autowired
	private SkyScannerConnector connector;

	@Autowired
	private RequestRepository requestRepository;

	public static void main(String[] args) {
		SpringApplication.run(AsepmEntryApplication.class, args);
	}

	@PostConstruct
	private void getInstances() {
		connector.getCurrencies();
		Request request = new Request();
		request.setName("Stefan");
		Solution solution = new Solution();
		solution.setRequest(request);
		request.getSol().add(solution);
		request = requestRepository.save(request);
		System.out.println(request.getId());

	}

}
