package com.lushun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lushun.entity.User;
import com.lushun.service.UserService;

@Controller
public class getUserController {

	private static final Log logger = LogFactory.getLog(getUserController.class);

	@Resource
	UserService service;

	@RequestMapping(value = "/getUser")
	public String selectUsers(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			logger.info("Code:" + fieldError.getCode() + ", object:" + fieldError.getObjectName() + ", field:"
					+ fieldError.getField());
			return "index";
		}
		List<User> users = service.getUsers(user);
		model.addAttribute("users", users);
		return "users";
	}

}
