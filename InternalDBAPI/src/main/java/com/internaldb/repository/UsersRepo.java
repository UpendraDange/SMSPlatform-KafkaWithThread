package com.internaldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.internaldb.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	boolean existsByUsernameAndPassword(String username, String password);


	@Query(value = "SELECT account_id FROM users WHERE username = :username AND password = :password", nativeQuery = true)
	Integer findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
