package com.pfms.Personal_Finance_Management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfms.Personal_Finance_Management.model.Category;
import com.pfms.Personal_Finance_Management.model.Goal;
import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.repository.CategoryRepository;
import com.pfms.Personal_Finance_Management.repository.GoalRepository;
import com.pfms.Personal_Finance_Management.repository.UserRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Map<String, String> updateGoal(String category, long user_id, double amount) {
		
		Category c = categoryRepo.findByCategory(category);
		List<Goal> goals = goalRepo.findAllByCategoryId(c.getCategoryId());
		System.out.println(goals);
		Map<String,String> response=new HashMap<String,String>();
		for(int i=0;i<goals.size();i++) {
			
			Goal g = goals.get(i);
			g.setVarAmount(g.getVarAmount()-amount);
			goalRepo.save(g);
			System.out.println(g.getVarAmount());
			double percentage= ((g.getVarAmount()/g.getGoalAmount())*100);
			if(percentage==50) {
				response.put("Amount",g.getVarAmount()+"");
				response.put("message","Goal 50% Is Completed" );
				
			}
			else if(percentage==80) {
				
				response.put("Amount",g.getVarAmount()+"");
				response.put("message","Goal 80% Is Completed " );
			}
			else if(percentage<50 & percentage>0) {
				
				response.put("Amount",g.getVarAmount()+"");
				response.put("message","Goal Is At Risk" );
			}
			else if (percentage<=0) {
				response.put("Amount", g.getVarAmount()+"");
				response.put("message","Goal limit exceeded " );
			}
			else {
				response.put("Amount",g.getVarAmount()+"");
				
			}
			System.out.println(amount);
			
		}
		return response;
		
		
	}
}
