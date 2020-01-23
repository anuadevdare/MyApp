package com.test.login.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

/**
 * User class has a special relationship with the database, directly
 * interact with the 'testmydatabase' database and 'user' table
 * 
 * @author Anita Devdare
 * @version 1.0
 * @since 2019-10-30
 */
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL_ID") })
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;
	@Column(name = "LAST_NAME", unique = false, nullable = true, length = 100)
	private String lastName;
	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 100)
	@Email(message = "Email id should be valid!")
	private String emailId;
	@Column(name = "PASSWORD", unique = false, nullable = false, length = 100)
	@Length(min = 5, message = "password must have at least 5 charater!")
	private String password;
	@Column(name = "PASSWORD_HINT", unique = false, nullable = true, length = 100)
	private String passwordHint;
	

	public User() {
		super();
	}

	public User(String firstName, String lastName, String userName, String password, String passwordHint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = userName;
		this.password = password;
		this.passwordHint = passwordHint;
	}

	public User(long uid, String firstName, String lastName, String emailId, String password, String passwordHint) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.passwordHint = passwordHint;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passward) {
		this.password = passward;
	}

	/**
	 * @return the passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @param passwordHint the passwordHint to set
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	

	@Override
	public String toString() {
		return "User [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", password=" + password + ", passwordHint=" + passwordHint + "]";
	}

	
}
