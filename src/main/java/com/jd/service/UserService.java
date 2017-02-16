package com.jd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jd.dao.UserDao;
import com.jd.entity.User;

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
