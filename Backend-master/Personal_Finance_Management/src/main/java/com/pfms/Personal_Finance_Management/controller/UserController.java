package com.pfms.Personal_Finance_Management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:8080/")
public class UserController {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/user_add")
	public String addUser(@RequestBody User user) {

//		String pwd=user.getPassword();
//		String encryptPwd=passwordEncoder.encode(pwd);
//		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "User added successfully";
	}
	
//	@PostMapping("/signin")
//	public ResponseEntity<String> authenticateUser(@RequestBody User user){		
//		User u=userRepository.findByEmail(user.getEmail());
//		if(u!=null) {
//			if(u.getPassword().equals(user.getPassword())) {
//				
//				return new ResponseEntity<>("User signed-in successfully !.",HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity<>("Wrong password",HttpStatus.NOT_FOUND);
//			}
//		}
//		else {
//			return new ResponseEntity<>("User not exist",HttpStatus.NOT_FOUND);
//		}
//	
//	}
	@PostMapping("/signin")
	public ResponseEntity<Map<String,String>> authenticateUser(@RequestBody User user){		
		User u=userRepository.findByEmail(user.getEmail());
		Map<String,String> response=new HashMap<String,String>();
		if(u!=null) {
			if(u.getPassword().equals(user.getPassword())) {
				response.put("msg","user sign in successfully");
				response.put("userName",u.getUserName());
				response.put("email",u.getEmail());
				response.put("id",u.getUser_id()+"");
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				response.put("msg","wrong password");
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}
		}
		else {
			response.put("msg", "user not exists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping("/listUser")
	public List<User> listofUsers(){
		
		return userRepository.findAll();
	}
}
