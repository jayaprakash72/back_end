package com.pfms.Personal_Finance_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pfms.Personal_Finance_Management.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

	User findByUserName(String username);
	
	User findByEmail(String email);

	@Query(value="SELECT user_id from user u where u.email= :email", nativeQuery = true)
	User getById(String email);

	@Query(value="SELECT user_id from user u where u.user_id= :uid", nativeQuery = true)
	User findById(long uid);

}
