package com.lushun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lushun.entity.User;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

}
