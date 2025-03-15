package com.threadapp.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.threadapp.config.AppConstants;
import com.threadapp.dto.ApiResponse;
import com.threadapp.entity.SendMsg;
import com.threadapp.repository.SendMsgRepo;
import com.threadapp.service.ThreadListnerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ThreadListnerServiceImpl implements ThreadListnerService {

	@Autowired
	private SendMsgRepo sendMsgRepo;

	@Autowired
	private WebClient webClient;

	@Override
	@Scheduled(fixedRate = 1000)
	public void msgThreadListner() {

		try {
			List<SendMsg> sendMsg = sendMsgRepo.findByStatus("NEW");
			for (SendMsg s : sendMsg) {
				log.info("Thread Schedular got record with id::"+s.getId());
				sendMsgRepo.updateStatusById(s.getId());
				ApiResponse response = webClient.get()
						.uri(uriBuilder -> uriBuilder.path("/telco/smsc/{account_id}/{mobile}/{message}") 
								.build(s.getAccountId(), s.getMobile(), s.getMessage())) 
						.retrieve().bodyToMono(ApiResponse.class).block();
				log.debug("API Response::" + response);
				s.setStatus("SENT");
				s.setTelcoResponse(response.getData().toString());
				s.setSendTs(LocalDateTime.now());
				sendMsgRepo.save(s);

			}
		} catch (Exception e) {
			log.error("Error:" + e.getMessage());
		}

	}

}
