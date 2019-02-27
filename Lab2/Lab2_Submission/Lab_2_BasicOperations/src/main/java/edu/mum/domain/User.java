package edu.mum.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity(name="users")
  public class User implements Serializable  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID", length=20)
     private Long id = null;

	@Column(name="FIRSTNAME", length=255, nullable=false)
     private String firstName;
	
	@Column(name="LASTNAME", length=255, nullable=false)
     private String lastName;

	@Column(name="EMAIL", length=255, nullable=false)
     private String email;

     @Column(name="RANKING", length=11, nullable=false)
     private int ranking = 0;

     @Column(name="IS_ADMIN", nullable=false)
     private boolean admin = false;
     
     @Version
     @Column(name="version", length=11, nullable=false)
     private int version = 0;

     @Temporal(TemporalType.TIMESTAMP)
     @Column(nullable=true)
     private Date lastLogin;
     
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}