package com.lushun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lushun.dao.UserDao;
import com.lushun.entity.User;

@Service
public class UserService {

	@Resource
	UserDao dao;
	
	public List<User> getUsers(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", user.getUserName());
		List<User> userList = dao.selectByParams(params);
		return userList;
	}
}
