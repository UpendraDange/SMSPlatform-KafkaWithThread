package com.internaldb.service;

import com.internaldb.dto.ApiResponse;
import com.internaldb.dto.KafkaRequestDto;
import com.internaldb.dto.SendMsgDto;

public interface UserService {

	ApiResponse validateUser(SendMsgDto sendMsgDto);

	ApiResponse storeMsg(KafkaRequestDto kafkaRequestDto);

}
