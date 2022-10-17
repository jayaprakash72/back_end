package com.pfms.Personal_Finance_Management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/auth")
public class ApplicationController {

	@GetMapping()
	public String Process() {
		return "passed the spring security through DB";
	}
}
