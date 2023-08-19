package com.ctel.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MyController {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private ObjectMapper objectMapper;

	public MyController(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@PostMapping("/post")
	public void sendObject(@RequestBody Data data) throws JsonProcessingException {

		try {

			kafkaTemplate.send("java", objectMapper.writeValueAsString(data));

			System.out.println("msg published to kafka" + data);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}