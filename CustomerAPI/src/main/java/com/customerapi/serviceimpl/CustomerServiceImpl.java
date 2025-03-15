package com.customerapi.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.customerapi.config.AppConstants;
import com.customerapi.dto.ApiResponse;
import com.customerapi.dto.KafkaRequestDto;
import com.customerapi.dto.SendMsgDto;
import com.customerapi.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private KafkaService kafkaService;

	@Autowired
	private WebClient webClient;

	@Override
	public ApiResponse sendMassage(SendMsgDto sendMsgDto) {
		log.info("Got API call request::");
		try {

			ApiResponse response = webClient.post().uri(AppConstants.WEB_CLIENT_IP + "/internaldb/validate/user")
					.bodyValue(sendMsgDto).retrieve().bodyToMono(ApiResponse.class).block();
			log.debug("API response ::" + response);
			if (response != null && response.getData() != null
					&& "failed".equalsIgnoreCase(response.getData().toString())) {
				return new ApiResponse(HttpStatus.BAD_REQUEST, AppConstants.USERNAME_OR_PASSWORD_IS_WRONG);
			} else if (response != null && response.getData() != null) {
				UUID uuid = UUID.randomUUID();
				String uuidInteger = String.valueOf(uuid);
				Integer acccountId = Integer.valueOf(response.getData().toString());
				KafkaRequestDto kafkaRequestDto = new KafkaRequestDto(uuidInteger, acccountId, sendMsgDto.getMobile(),
						sendMsgDto.getMessage());
				kafkaService.updateSms(kafkaRequestDto);
				return new ApiResponse(HttpStatus.OK, uuid, "");
			} else if (response != null && response.getError() != null) {

				return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR);
			}

			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error("Error:" + e.getMessage());
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR);
		}
	}

}
