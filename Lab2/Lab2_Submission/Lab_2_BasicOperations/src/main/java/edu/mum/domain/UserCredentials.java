package edu.mum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

 
@Entity(name="authentication")
public class UserCredentials {

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)  //because no auto-increment for username
	@Column(name="user", length=127)
  	String username;
	
	@Column(name="PASSWORD", length=32)
 	String password;
	
	@Transient
 	String verifyPassword;
	
	Boolean enabled;

 	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyPassword() {
		return verifyPassword;
	}
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
 	
}
