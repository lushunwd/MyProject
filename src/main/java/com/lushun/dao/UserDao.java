package com.lushun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lushun.entity.User;

@Repository
public interface UserDao {
	List<User> selectByParams(Map<String, Object> params);
}
