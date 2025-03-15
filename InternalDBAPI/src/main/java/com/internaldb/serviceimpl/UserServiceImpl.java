package com.internaldb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.internaldb.config.AppConstants;
import com.internaldb.dto.ApiResponse;
import com.internaldb.dto.KafkaRequestDto;
import com.internaldb.dto.SendMsgDto;
import com.internaldb.entity.SendMsg;
import com.internaldb.repository.SendMsgRepo;
import com.internaldb.repository.UsersRepo;
import com.internaldb.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private SendMsgRepo sendMsgRepo;

	@Override
	public ApiResponse validateUser(SendMsgDto sendMsgDto) {

		log.info("Got API call request to validate user");
		try {
			if (usersRepo.existsByUsernameAndPassword(sendMsgDto.getUsername(), sendMsgDto.getPassword())) {
				Integer accountId = usersRepo.findByUsernameAndPassword(sendMsgDto.getUsername(),
						sendMsgDto.getPassword());
				return new ApiResponse(HttpStatus.OK, accountId, "");
			} else {
				return new ApiResponse(HttpStatus.OK, "failed", "");
			}
		} catch (Exception e) {
			log.error("Error:" + e.getMessage());
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ApiResponse storeMsg(KafkaRequestDto kafkaRequestDto) {
		log.debug("Got API call request to store msg"+ kafkaRequestDto);
		try {
			SendMsg sendMsg = new SendMsg(kafkaRequestDto);
			sendMsgRepo.save(sendMsg);
			return new ApiResponse(HttpStatus.OK, "success", "");
		} catch (Exception e) {
			log.error("Error:" + e.getMessage());
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR);
		}
	}

}
