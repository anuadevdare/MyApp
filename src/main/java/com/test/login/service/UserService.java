package com.test.login.service;

import java.util.List;

import javax.validation.Valid;

import com.test.login.dto.ChangePassword;
import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.LoginDto;
import com.test.login.dto.UpadateUserDTO;
import com.test.login.dto.UserListDto;
import com.test.login.dto.UserResponseDto;
import com.test.login.entity.User;
/**
 * ExpenseService interface has declared to registers the user and login APIs operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-30
 */
public interface UserService {

	UserResponseDto registerUser(User user);

	ExpenseDtoResp login(LoginDto loginDto);

	UserResponseDto updateUser(@Valid UpadateUserDTO user, long uid);

	UserResponseDto deleteUser(long uid);

	List<UserListDto> userList();

	UserResponseDto getUserById(long uid);

	UserResponseDto changePassword(ChangePassword changePassword);
}
