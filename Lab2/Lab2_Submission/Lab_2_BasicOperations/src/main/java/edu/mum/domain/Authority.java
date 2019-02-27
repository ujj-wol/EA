package edu.mum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="authority")
public class Authority {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length=20)
 	private long id;

	@Column(length=255)
	private String username;
	
	@Column(length=255)
 	private String authority;
 	
	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
 
 	
}
