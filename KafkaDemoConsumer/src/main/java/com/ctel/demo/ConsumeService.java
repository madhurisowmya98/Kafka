package com.ctel.demo;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumeService {

	/*
	 * @KafkaListener(topics = "includeurtopicname", groupId = "group_user",
	 * containerFactory = "kafkaListenerContainerFactory") 
	 * public void gettingData(String data) {
	 * System.out.println(">>>>>>>>>Msg received from publisher" + data);
	 * 
	 * }
	 */
	//private  static final Logger LOGGER = LoggerFactory.getLogger(ConsumeService.class);

	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "java")

	public void kafkaListener(String message) throws JsonMappingException, JsonProcessingException, SocketException {

		Data data = objectMapper.readValue(message, Data.class);
		System.out.println(data+ ">>>>>>>>> msg received from kafka"); 
	}

}
