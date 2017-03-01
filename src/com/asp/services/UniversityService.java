package com.asp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.dao.UniversityDao;

@Service("universityService")
public class UniversityService {
		
		@Autowired
		private UniversityDao universityDao;

		public boolean exists(String email) {
			return universityDao.exists(email);
		}
}
