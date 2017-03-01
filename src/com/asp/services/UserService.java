package com.asp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.dao.Users;
import com.asp.dao.UsersDao;

@Service("usersService")
public class UserService {

	
	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	public void create(Users user) {
		usersDao.create(user);
		
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	public List<Users> getUserDetails(String username) {
		return usersDao.getUserDetails(username);
	}

	public List<Users> getAllUsers() {
		return usersDao.getAllUsers();
	}

	public Users getUser(String username) {
		return usersDao.getUser(username);
	}

	public boolean emailExists(String email) {
		return usersDao.existEmail(email);
	}

	public Users getUserWithEmail(String email) {
		return usersDao.getUserWithEmail(email);
	}

	public void resetPassword(String username, String password) {
		usersDao.resetPassword(username,password);
	}

	public Users getUsernameWithFullname(String receivername) {
		return usersDao.getUsernameWithFullname(receivername);
	}

	
}
