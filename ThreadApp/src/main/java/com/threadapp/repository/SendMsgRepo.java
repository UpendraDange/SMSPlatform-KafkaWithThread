package com.threadapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.threadapp.entity.SendMsg;

@Repository
public interface SendMsgRepo extends JpaRepository<SendMsg, Integer> {

	List<SendMsg> findByStatus(String status);

	@Modifying
    @Transactional
    @Query(value = "UPDATE send_msg SET status = 'INPROCESS' WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id);

}
