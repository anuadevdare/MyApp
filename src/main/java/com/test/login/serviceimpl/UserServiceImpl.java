package com.test.login.serviceimpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.login.dao.UserDao;
import com.test.login.dao.UserDaoImpl;
import com.test.login.dto.ChangePassword;
import com.test.login.dto.ExpenseDtoResp;
import com.test.login.dto.LoginDto;
import com.test.login.dto.UpadateUserDTO;
import com.test.login.dto.UserListDto;
import com.test.login.dto.UserResponseDto;
import com.test.login.entity.User;
import com.test.login.exception.PasswardInValid;
import com.test.login.exception.UserNotFoundException;
import com.test.login.service.ExpenseService;
import com.test.login.service.UserService;

/**
 * ExpenseService class has implemented to registers the user and login APIs
 * operation methods
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-30
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl userDao;
	@Autowired
	UserDao userDaoImp;
	@Autowired
	ExpenseService expenseService;
	UserResponseDto responseDto = null;
	@Autowired
	BCryptPasswordEncoder encoder;

	/**
	 * Registers the user to be saved to the Database
	 *
	 * @param user object all details of user
	 * @return return the the proper massage that the UserResponseDto object has
	 *         been registered
	 */
	@Override
	public UserResponseDto registerUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		String massage = user.getFirstName() + " " + user.getLastName();
		User userData = userDaoImp.save(user);

		if (userData == null) {
			return new UserResponseDto(massage + "is not registered!.", HttpStatus.BAD_REQUEST.toString(), userData);
		}
		return new UserResponseDto(massage + " is registered successfully!.", HttpStatus.CREATED.toString(), userData);
	}

	/**
	 * Login the user as per credentials
	 * 
	 * @param loginDto loginDto has required details of for login
	 * @return return all expenses details with expenses total
	 * @throws PasswardInValid for invalid password and UserNotFoundException for invalid user
	 */
	@Override
	public ExpenseDtoResp login(LoginDto loginDto) {
		String emailId = loginDto.getEmailId();
		User userDetails = userDao.findByEmailId(emailId);
		if (userDetails == null)
			throw new UserNotFoundException("Email id '" + emailId + "' is invalid.Please enter the valid email id.!");

		boolean isPasswordMatches = encoder.matches(loginDto.getPassword(), userDetails.getPassword());

		if (isPasswordMatches) {
			return expenseService.findByUserExpenses(userDetails.getUid());
		} else {
			throw new PasswardInValid("Invalid password.Please,Enter valid password!");
		}
	}

	/**
	 * Update the user data
	 * 
	 * @param user object has required details of for update user and uid for the user id
	 * @return return the user details
	 */
	@Override
	public UserResponseDto updateUser(@Valid UpadateUserDTO user, long uid) {
		User userData = userDao.findById(uid);
		if (userData == null) {
			return new UserResponseDto("User is not present with uid: " + uid + ".Please enter valid userId..!",
					HttpStatus.BAD_REQUEST.toString(), null);
		}
		if (user.getFirstName() != null) {
			userData.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			userData.setLastName(user.getLastName());
		}
		if (user.getEmailId() != null) {
			userData.setEmailId(user.getEmailId());
		}

		return new UserResponseDto(
				userData.getFirstName() + " " + userData.getLastName() + " is updated successfully!.",
				HttpStatus.OK.toString(), userDaoImp.save(userData));

	}

	/**
	 * Delete the user from the database
	 * 
	 * @param uid the user Id for delete user from the database
	 * @return return the user deleted proper massage
	 */
	@Override
	public UserResponseDto deleteUser(long uid) {
		User userData = userDao.findById(uid);
		if (userData == null) {
			return new UserResponseDto("User is not present with uid: " + uid + ".Please enter valid userId..!",
					HttpStatus.BAD_REQUEST.toString());
		}
		userDaoImp.deleteById(uid);
		return new UserResponseDto("User deleted sucessfully: " + uid + ".", HttpStatus.OK.toString());
	}

	/**
	 * List out all users from the database
	 * 
	 * @return return user list
	 */

	@Override
	public List<UserListDto> userList() {
		return userDao.userList();
	}

	/**
	 * Get the user from the database
	 * 
	 * @param uid the user Id for get user from the database
	 * @return UserResponseDto the user details
	 */

	@Override
	public UserResponseDto getUserById(long uid) {
		User userData = userDao.findById(uid);
		if (userData == null) {
			return new UserResponseDto("User is not present with uid: " + uid + ".Please enter valid userId..!",
					HttpStatus.BAD_REQUEST.toString());
		}
		return new UserResponseDto(
				userData.getFirstName() + " " + userData.getLastName() + " is retrived successfully!.",
				HttpStatus.OK.toString(), userData);
	}

	/**
	 * change the user password
	 * 
	 * @param changePassword the required data for change user object
	 * @return return the password changed massage
	 * @throws UserNotFoundException for invalid password and emailId
	 */
	@Override
	public UserResponseDto changePassword(ChangePassword changePassword) {
		String emailId = changePassword.getEmailId();

		User userDetails = userDao.findByEmailId(emailId);
		if (userDetails == null)
			throw new UserNotFoundException("Email id '" + emailId + "' is invalid.Please enter the valid email id.!");

		boolean isPasswordMatches = encoder.matches(changePassword.getOldPassword(), userDetails.getPassword());

		if (isPasswordMatches) {
			if (changePassword.getNewPassword().equals(changePassword.getConfNewPassword())) {
				userDetails.setPassword(changePassword.getNewPassword());
				userDetails.setPasswordHint(changePassword.getPasswordHint());
				userDetails.setPassword(encoder.encode(userDetails.getPassword()));
				userDaoImp.save(userDetails);
				return new UserResponseDto(userDetails.getFirstName() + " " + userDetails.getLastName()
						+ " your password changed successfully!.", HttpStatus.OK.toString());

			} else {

				return new UserResponseDto("New password and confirm password must be same!",
						HttpStatus.BAD_REQUEST.toString());
			}
		} else {
			throw new PasswardInValid("Invalid password.Please,Enter valid password!");
		}

	}
}
