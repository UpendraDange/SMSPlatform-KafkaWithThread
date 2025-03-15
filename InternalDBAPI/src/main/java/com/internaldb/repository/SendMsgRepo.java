package com.internaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internaldb.entity.SendMsg;

@Repository
public interface SendMsgRepo extends JpaRepository<SendMsg, Integer> {

}
