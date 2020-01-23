package com.test.login.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.login.dto.ChangePassword;
import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.LoginDto;
import com.test.login.dto.UpadateUserDTO;
import com.test.login.dto.UserListDto;
import com.test.login.dto.UserResponseDto;
import com.test.login.entity.User;
import com.test.login.service.UserService;

/**
 * UserController is a controller to call registers the user and login APIs for
 * users that is called by URL starting with "/expense"
 *
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-04
 */
@RestController
@RequestMapping(value = "/expense")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * Registers the user to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/registeruser) through the post method
	 * 
	 * @param user object all details of user in the json format
	 * @return return the the proper massage that the user object has been
	 *         registered
	 */
	@PostMapping(path = "/registeruser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseDto register(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}

	/**
	 * update the user to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/updateuser/{uid}) through the put method
	 * 
	 * @param user object all details of user in the json format uid user Id for
	 *             updating the user
	 * @return return the the proper massage that the user object has been updated
	 */
	@PutMapping(path = "/updateuser/{uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseDto updateUser(@RequestBody UpadateUserDTO user, @PathVariable long uid) {
		return userService.updateUser(user, uid);
	}

	/**
	 * delete the user to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/deleteuser/{uid}) through the delete
	 * method
	 * 
	 * @param uid user Id for deleting the user from database
	 * @return return the the proper massage that the user object has been deleted
	 */
	@DeleteMapping(path = "/deleteuser/{uid}")
	public UserResponseDto deleteUser(@PathVariable long uid) {
		return userService.deleteUser(uid);
	}

	/**
	 * get the user from the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/getuser/{uid}) through the delete method
	 * 
	 * @param uid user Id for getting the user from database
	 *
	 * @return return the user object has been
	 */
	@GetMapping(path = "/getuserbyid/{uid}")
	public UserResponseDto getUserById(@PathVariable long uid) {
		return userService.getUserById(uid);
	}

	/**
	 * update the user to be saved to the Database
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/userList) through the get method
	 * @return return the user list
	 * 
	 */
	@GetMapping(path = "/userList")
	public List<UserListDto> allUsers() {
		return userService.userList();
	}

	/**
	 * Login the user as per credentials
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/login) through the post method
	 * 
	 * @param loginDto object has required details of for login in the json format
	 * @return return all expenses details with expenses total in the json format
	 */
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExpenseDtoResp login(@RequestBody LoginDto loginDto) {
		return userService.login(loginDto);
	}

	/**
	 * Change password for user
	 * 
	 * {@link} url an absolute URL giving the base location of the
	 * users(http://localhost:8082/expense/login) through the post method
	 * 
	 * @param changePassword object has required details of for change in the json
	 *                       format
	 * @return return all expenses details with expenses total in the json format
	*/
	@PostMapping(path = "/changepassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseDto changePassword(@RequestBody ChangePassword changePassword) {
		return userService.changePassword(changePassword);
	}
}
