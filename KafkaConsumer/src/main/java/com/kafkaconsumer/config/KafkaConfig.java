package com.kafkaconsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaconsumer.dto.ApiResponse;
import com.kafkaconsumer.dto.KafkaRequestDto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaConfig {

	@Autowired
	private WebClient webClient;

	@KafkaListener(topics = AppConstunts.SMS_UPDATE_TOPIC, groupId = AppConstunts.GROUP_ID)
	public void updateSms(String value) {

		log.debug("Kafka Consume data"+value);
		ObjectMapper mapper = new ObjectMapper();
		try {
			KafkaRequestDto kafkaRequestDto = mapper.readValue(value, KafkaRequestDto.class);

			ApiResponse response = webClient.post().uri(AppConstunts.WEB_CLIENT_IP + "/internaldb/store/msg")
					.bodyValue(kafkaRequestDto).retrieve().bodyToMono(ApiResponse.class).block();
			log.info("API Response::" + response);
		} catch (JsonMappingException e) {
			log.error(AppConstunts.ERROR + e.getMessage());
		} catch (JsonProcessingException e) {
			log.error(AppConstunts.ERROR + e.getMessage());
		} catch (Exception e) {
			log.error(AppConstunts.ERROR + e.getMessage());
		}

	}
}
