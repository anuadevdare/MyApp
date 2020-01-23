package com.test.login.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.login.LoginDemoFinalApplication;
import com.test.login.dto.LoginDto;
import com.test.login.entity.Expense;
import com.test.login.entity.User;

/**
 * integrated positive and negative Login demo API test cases In the
 * LoginDemoApplicationTestsInTest Class
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-11-08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LoginDemoFinalApplication.class)
@AutoConfigureMockMvc
@WithMockUser("/user-1")
@ActiveProfiles("test")

public class LoginDemoApplicationTestsInTest {
	private static final String URL = "/expense";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	private static final Logger logger = LogManager.getLogger(LoginDemoApplicationTestsInTest.class);

	/**
	 * Create a valid User.
	 * 
	 * @throws Exception if invalid user data entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void createUser() throws Exception {

		String jsonRequest = asJsonString(mockUser());
		logger.info("Check data******" + jsonRequest);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/registeruser")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockUser()));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getContentAsString());

	}

	/**
	 * Create a valid Expense.
	 * 
	 * @throws Exception if invalid user data entered
	 */
	@WithMockUser("/user-1")
	@Test
	public void saveExpence() throws Exception {
		createUser();
		Expense expense = new Expense(1L, "pen", "Grocery", "pen for employee", 1000, 1L, new Date());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/save")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(expense));
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
	}

	/**
	 * Retrieves list of Expenses.
	 * 
	 * @throws Exception if invalid user data entered
	 */
	@WithMockUser("/user-1")
	@Test
	public void retrieveListExpences() throws Exception {
		saveAllUsers();
		saveAllExpences();
		RequestBuilder requestBuilderall = MockMvcRequestBuilders.get(URL + "/allexpences")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilderall).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getCharacterEncoding().toString());

	}

	/**
	 * Login the user with valid credentials
	 * 
	 * @throws Exception if invalid user data entered
	 */
	@WithMockUser("/user-1")
	@Test
	public void loginUser() throws Exception {
		saveAllUsers();
		saveAllExpences();
		String jsonRequest = asJsonString(loginData());
		logger.info("Request is:" + jsonRequest);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/login")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginData()));
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getCharacterEncoding().toString());

	}

	/**
	 * try to login the user with invalid emailid
	 * 
	 * @throws Exception if invalid user data entered
	 */
	@WithMockUser("/user-1")
	@Test
	public void loginUserWithIvalidEmailId() throws Exception {
		saveAllUsers();
		saveAllExpences();
		String jsonRequest = asJsonString(new LoginDto("seema@gmail.com", "seema123"));
		logger.info("Request is:" + jsonRequest);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/login")
				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		logger.info("Response is: " + result.getResponse().getContentAsString());
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
		assertEquals("Email id 'seema@gmail.com' is invalid.Please enter the valid email id.!",
				result.getResponse().getContentAsString());

	}

	/**
	 * try to login the user with invalid password
	 * 
	 * @throws Exception if invalid user data entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void loginUserWithIvalidPassWord() throws Exception {
		saveAllUsers();
		saveAllExpences();
		String jsonRequest = asJsonString(new LoginDto("seemapatil@gmail.com", "seema12"));
		logger.info("Request is:" + jsonRequest);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/login")
				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		logger.info("Response is: " + result.getResponse().getContentAsString());
		assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
		assertEquals("Invalid password.Please,Enter valid password!", result.getResponse().getContentAsString());

	}

	/**
	 * get a the User by Id.
	 * 
	 * @throws Exception if Id entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void getUserById() throws Exception {
		saveAllUsers();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL + "/getuserbyid/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getContentAsString());

	}

	/**
	 * get a the expense by Id.
	 * 
	 * @throws Exception if invalid Id entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void getExpenceById() throws Exception {
		saveAllUsers();
		saveAllExpences();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL + "/getexpense/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getContentAsString());

	}

	/**
	 * delete the User by Id.
	 * 
	 * @throws Exception if invalid Id entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void deleteUserById() throws Exception {
		saveAllUsers();
		saveAllExpences();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL + "/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.get(URL + "/getexpense/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result1.getResponse().getStatus());
		logger.info("Response is: " + result1.getResponse().getContentAsString());

	}

	/**
	 * delete the expense by Id.
	 * 
	 * @throws Exception if invalid Id entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void deleteExpenseById() throws Exception {
		saveAllUsers();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL + "/deleteuser/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.get(URL + "/getexpense/" + 1)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();
		assertEquals(HttpStatus.OK.value(), result1.getResponse().getStatus());
		logger.info("Response is: " + result1.getResponse().getContentAsString());

	}

	/**
	 * update the expense by Id.
	 * 
	 * @throws Exception if invalid Id entered
	 */

	@WithMockUser("/user-1")
	@Test
	public void updateExpenseById() throws Exception {
		saveAllUsers();
		saveAllExpences();
		Expense expense = new Expense("pen", "Grocery", "pen for company", 2000, 1L, new Date());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL + "/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(expense));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		logger.info("Response is: " + result.getResponse().getContentAsString());

	}

	/**
	 * persist list of Expenses.
	 * 
	 * @throws Exception if invalid Id entered
	 */
	public void saveAllExpences() throws Exception {
		ListIterator<Expense> iterator = mockExpense().listIterator();
		while (iterator.hasNext()) {
			Expense expense = iterator.next();
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/save")
					.contentType(MediaType.APPLICATION_JSON).content(asJsonString(expense));
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			logger.info("Response is: " + result.getResponse().getCharacterEncoding().toString());

		}
	}

	/**
	 * persist list of User.
	 * 
	 * @throws Exception if invalid user data entered
	 */
	public void saveAllUsers() throws Exception {
		ListIterator<User> iterator = mockUsers().listIterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL + "/registeruser")
					.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user));
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			logger.info("Response is: " + result.getResponse().getCharacterEncoding().toString());

		}
	}

	/**
	 * Login data.
	 * 
	 * @return return the LoginDto object
	 */
	private LoginDto loginData() {
		return new LoginDto("seemapatil@gmail.com", "seema123");
	}

	/**
	 * User object for resister user.
	 * 
	 * @return return the User object
	 */
	private User mockUser() {
		return new User("Seema", "Patil", "seemapatil@gmail.com", "seema123", "nikename");

	}

	/**
	 * List of user objects for resister users.
	 * 
	 * @return return List of Users
	 */
	private List<User> mockUsers() {
		List<User> uList = new ArrayList<>();
		uList.add(new User("Seema", "Patil", "seemapatil@gmail.com", "seema123", "nikename"));
		uList.add(new User("Reema", "Patil", "reemapatil@gmail.com", "reema123", "nikename"));
		uList.add(new User("Neema", "Patil", "neemapatil@gmail.com", "seema123", "nikename"));
		return uList;

	}

	/**
	 * List of expense objects for save expenses.
	 * 
	 * @return return List of expenses
	 */
	private List<Expense> mockExpense() {
		List<Expense> eList = new ArrayList<>();
		eList.add(new Expense(1L, "pen", "Grocery", "pen for employee", 1000, 1L, new Date()));
		eList.add(new Expense(2L, "Electricity bill", "Electricity", "Electricity bill", 5000, 1L, new Date()));
		eList.add(new Expense(3L, "Food for client", "Food", "Food for client", 10000, 2L, new Date()));
		return eList;
	}

	/**
	 * convert JSON to String
	 * 
	 * @param obj for converting to String
	 * @return string from the JSON object
	 */

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
