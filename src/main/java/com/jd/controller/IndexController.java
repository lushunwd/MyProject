package com.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jd.entity.User;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

}
