package com.web.rest.retail.module.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	
	@GetMapping("/getData")
	public String getHomeData(){
		
		return "My Home Page";
	}
}
