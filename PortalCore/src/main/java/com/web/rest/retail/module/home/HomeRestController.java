package com.web.rest.retail.module.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.service.retail.module.home.HomeService;

@RestController
@CrossOrigin
public class HomeRestController {
	
	@Autowired
	private HomeService homeService;
	
	@GetMapping("/getData")
	public String getHomeData(){
		System.out.println("rest called");
		return "My Home Page";
	}
}
