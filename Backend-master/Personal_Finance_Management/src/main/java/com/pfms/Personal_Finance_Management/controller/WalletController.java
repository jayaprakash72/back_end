package com.pfms.Personal_Finance_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.model.Wallet;
import com.pfms.Personal_Finance_Management.repository.UserRepository;
import com.pfms.Personal_Finance_Management.repository.WalletRepository;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/user")
public class WalletController {
		
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@PostMapping(value="/addwallet")
	public String addWallet(@RequestBody Wallet wallet ,@RequestParam String email) {
		
//		User u=userRepository.findByEmail(user.getEmail());
//		//System.out.println(addwallet.user.getEmail());
//		System.out.println("HIHLOOOOOOO");
//		wallet.setUid(u.getUser_id());
		User u=userRepository.findByEmail(email);
		wallet.setUid(u.getUser_id());
		walletRepository.save(wallet);
		return "wallet added";
	}
	
	@GetMapping("/wallets")
	public List<Wallet> getWallets(@RequestParam String email){
		//System.out.println(user.getEmail());
		//User u=userRepository.getById(user.getEmail());
		User u=userRepository.findByEmail(email);
		System.out.println(u.getUser_id());
		return walletRepository.findAllById(u.getUser_id());
		//return " ";
	}
	
	@GetMapping("/wallet_balance")
	public Double getBalance(@RequestParam String email,@RequestParam String walletName ) {
		
		User u=userRepository.findByEmail(email);
		Wallet w= walletRepository.findByIdAndName(u.getUser_id(), walletName);
		return w.getWallet_amount();
		
	}
	
	@GetMapping("/deleteWallet")
	public String deleteWallet(@RequestParam String email,@RequestParam String walletName) {
		
		User u=userRepository.findByEmail(email);
		walletRepository.deleteWalletByIdAndName(u.getUser_id(),walletName);
		return "wallet Deleted";
		
	}

	
}