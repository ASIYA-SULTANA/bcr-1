package org.accion.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import org.accion.entity.User;
import org.accion.service.IUserService;

@Controller
@RequestMapping("bcr")
//@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
	@Autowired
	private IUserService userService;
	@GetMapping("user")
	public ResponseEntity<User> getUserByUserName(@RequestParam("id") String id) {
		User user = userService.getUserByUserName(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@GetMapping("allusers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	@PostMapping("user")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
        boolean flag = userService.createUser(user);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user?id={id}").buildAndExpand(user.getUserName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@DeleteMapping("user")
	public ResponseEntity<Void> deleteUser(@RequestParam("id") String id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
} 