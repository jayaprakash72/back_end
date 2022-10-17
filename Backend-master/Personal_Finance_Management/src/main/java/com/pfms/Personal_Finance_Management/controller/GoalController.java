package com.pfms.Personal_Finance_Management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfms.Personal_Finance_Management.model.Category;
import com.pfms.Personal_Finance_Management.model.Goal;
import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.repository.CategoryRepository;
//import com.pfms.Personal_Finance_Management.model.Wallet;
import com.pfms.Personal_Finance_Management.repository.GoalRepository;
import com.pfms.Personal_Finance_Management.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/goal")
public class GoalController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private GoalRepository goalRepo;
	
	
	
	
	@PostMapping("/addgoal")
	public String addGoal(@RequestBody Goal goal,@RequestParam String email,@RequestParam List<String> category) {
		
		User u= userRepo.findByEmail(email);
		for(int i=0;i<category.size();i++) {
			
			System.out.println("*************");
			System.out.println((String)category.get(i));
			Category c=categoryRepo.findByCategory((String) category.get(i));
			System.out.println(c);
			Long l =null;
			try {
				l=goalRepo.findLastID()+1;
			}
			catch (NullPointerException e) {
				// TODO: handle exception
				l=(long) 1;
			}
			goal.setGid(l);
			goal.setCid(c.getCategoryId());
			goal.setUid(u.getUser_id());
			goal.setVarAmount(goal.getGoalAmount());
			goalRepo.save(goal);
		}

		return "GOAL ADDED";
	}
	
	@GetMapping("/goals")
	public List<Goal> getGoals(@RequestParam String email){
			
		User u= userRepo.findByEmail(email);
		return goalRepo.findAllByID(u.getUser_id());
	}
	
	@GetMapping(value="/deletegoal")
	public String deleteGoal(@RequestParam String goalName) {
		
		Goal g = goalRepo.findByName(goalName);
		System.out.println(g.getGoalName());
		goalRepo.deleteById(g.getGid());
		return "GOAL DELETED";
	}

}