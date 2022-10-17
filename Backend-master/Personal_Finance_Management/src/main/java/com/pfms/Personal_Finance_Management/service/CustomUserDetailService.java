package com.pfms.Personal_Finance_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=repository.findByUserName(username);
		CustomUserDetails userDetails=null;
		if(user!=null) {
			userDetails=new CustomUserDetails();
			userDetails.setUser(user);
		}
		else {
			throw new UsernameNotFoundException("User not exist with the name"+  username);
			
		}
		
		return userDetails;
	}

}
