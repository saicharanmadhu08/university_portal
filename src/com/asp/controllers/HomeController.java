package com.asp.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.asp.dao.Comments;
import com.asp.dao.Likes;
import com.asp.dao.Posts;
import com.asp.dao.Users;
import com.asp.services.PostService;
import com.asp.services.UserService;

@Controller
public class HomeController {
	
	private Posts post;
	@Autowired
	UserService userService = new UserService();

	@Autowired
	PostService postService = new PostService();
	
	

	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("posts", new Posts());
		return "home";
	}
	

	
	@RequestMapping("/settings")
	public String showSettings() {
		return "settings";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}

	@RequestMapping(value = "/getUserDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserDetails(Principal principal) {

		List<Users> userDetails = null;

		if (principal == null) {
			userDetails = new ArrayList<Users>();
		} else {
			String username = principal.getName();
			userDetails = userService.getUserDetails(username);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userDetails", userDetails);

		return data;
	}


	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllUsers() {

		List<Users> allUsers = null;
		allUsers = userService.getAllUsers();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("allUsers", allUsers);

		return data;
	}

	@RequestMapping(value = "/doPost", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> createPost(Principal principal, @RequestBody Map<String, Object> data) {
		
		String text = (String)data.get("text");
		String category = (String)data.get("category");
		String username = principal.getName();
		System.out.println(text);
		System.out.println(category);
		System.out.println(username);
		//post.setText(text);
		//post.setUsername(username);
		post = new Posts(username, text,category);
		postService.createPost(post);
		
		Map<String, Object> success = new HashMap<String, Object>();
		data.put("Data", "posted");
		
		return success;
	}
	@RequestMapping(value = "/getAllPosts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAllPosts() {
		List<Posts> allPosts = null;
		allPosts = postService.getAllPosts();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("allPosts", allPosts);

		return data;
		
	}
	
	@RequestMapping(value = "/getProfilePosts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getProfilePosts(Principal principal) {
		List<Posts> profilePosts = null;
		String username = principal.getName();
		profilePosts = postService.getProfilePosts(username);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("profilePosts", profilePosts);

		return data;
		
	}
	
	@RequestMapping(value = "/updateLikes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean updateLikes(Principal principal,@RequestBody Map<String, Object> data){
		
		String likername = principal.getName();
		int postid = Integer.parseInt((String) data.get("postID"));
		Likes likes = new Likes();
		likes.setLikername(likername);
		likes.setPostid(postid);
		if(postService.checkLikers(likes)){			
			return postService.checkLikers(likes);
		}
		else{
			postService.updateLikes(likes);	
			return false;
		}
		
	}
	
	@RequestMapping(value = "/getLikesInfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getLikesInfo(@RequestParam("postid") int postid){
		List<Likes> profileLikes = null;
		profileLikes = postService.getLikesInfo(postid);
		
		Map<String, Object> likesList = new HashMap<String, Object>();
		likesList.put("profileLikes", profileLikes);

		return likesList;
		
	}
	
	@RequestMapping(value = "/sharePost", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void sharePost(Principal principal,@RequestBody Map<String, Object> data){
		
		String sharedby = principal.getName();
		int postID = Integer.parseInt((String) data.get("postid"));
		Posts thisPost = postService.getPost(postID);
		//String actualUser = thisPost.getUsername();
		//String actualText = thisPost.getText();
		String sharedData = "http://localhost:8080/university-portal/posts?postid="+postID;
		post = new Posts(sharedby, sharedData);
		postService.createPost(post);

	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object>  getSharedPost(@RequestParam("postid") int postID){
		Posts thisPost = postService.getPost(postID);
		String intermediateText = thisPost.getText();
		String[] parts = intermediateText.split("postid=");
		String requiredPostid = parts[1];
		Posts requiredpost = postService.getPost(Integer.parseInt(requiredPostid));
		String actualUser = requiredpost.getUsername();
		String actualText =requiredpost.getText();
		Map<String, Object> postDetails = new HashMap<String, Object>();
		postDetails.put("actualText", actualText);
		postDetails.put("actualUser", actualUser);

		return postDetails;
	}
	
	@RequestMapping(value="/doComment", method = RequestMethod.POST, produces ="application/json" )
	@ResponseBody
	public void doComment(Principal principal, @RequestBody Map<String, Object> data){
		
		String username = principal.getName();
		String commentText = (String) data.get("commentText");
		int postid =  Integer.parseInt((String) data.get("postid"));
		
		Comments comments = new Comments();
		comments.setPostid(postid);
		comments.setText(commentText);
		comments.setUsername(username);
		
		postService.doComment(comments);
	}
	
	@RequestMapping(value="/getComments", method = RequestMethod.GET, produces ="application/json" )
	@ResponseBody
	public Map<String, Object> getComments(@RequestParam("postid") int postid){
		
		List<Comments> commentForPost = postService.checkIfCommentsExists(postid);
		System.out.println(commentForPost.size());
		if(commentForPost.size()>0){
			List<Comments> postComments = null;
			postComments = postService.getComments(postid);
			
			Map<String, Object> comments = new HashMap<String, Object>();
			comments.put("postComments", postComments);

			return comments;
		}else{
			Map<String, Object> comments = new HashMap<String, Object>();
			comments.put("commentForPost", commentForPost.size());

			return comments;
		}
		
		
	}
	
  
	
}
