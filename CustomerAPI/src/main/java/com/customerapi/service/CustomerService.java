package com.customerapi.service;

import com.customerapi.dto.ApiResponse;
import com.customerapi.dto.SendMsgDto;

public interface CustomerService {

	ApiResponse sendMassage(SendMsgDto sendMsgDto);

}
