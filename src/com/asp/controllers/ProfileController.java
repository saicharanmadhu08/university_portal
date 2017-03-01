package com.asp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asp.dao.Posts;
import com.asp.dao.Users;
import com.asp.services.PostService;
import com.asp.services.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	UserService userService = new UserService();

	@Autowired
	PostService postService = new PostService();

	@RequestMapping("/profile")
	public String showProfile(@RequestParam("username") String username) {
		return "profile";
	}
	
	@RequestMapping(value = "/getProfileDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserDetails(@RequestParam("profilename") String profilename) {

		List<Users> profileDetails = null;

		if (profilename == null) {
			profileDetails = new ArrayList<Users>();
		} else {
			profileDetails = userService.getUserDetails(profilename);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("profileDetails", profileDetails);

		return data;
	}
	
	@RequestMapping(value = "/getProfileUserPosts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getProfilePosts(@RequestParam("profilename") String profilename) {
		List<Posts> profilePosts = null;
		profilePosts = postService.getProfilePosts(profilename);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("profilePosts", profilePosts);

		return data;
		
	}
}
