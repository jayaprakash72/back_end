package com.pfms.Personal_Finance_Management.service;


import org.springframework.stereotype.Service;



@Service
public class WalletService {
	
	public  double  setAmount(String category,double tamount,double wamount) {
		
		String cat="credit";
		if(category.equals(cat)) {
			
			wamount=wamount+tamount;
		}
		else {
			
			wamount=wamount-tamount;
		}
	
		return wamount;
	}

}
