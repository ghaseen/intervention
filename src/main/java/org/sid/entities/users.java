package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Entity
@Table(name="users")
public class users implements Serializable{
	@Id
private String username ;
private String password ;
private boolean active ;
private Client  client;
public users(String username, String password, boolean active) {
	super();
	this.username = username;
	this.password = password;
	this.active = active;
}
public users() {
	super();
	// TODO Auto-generated constructor stub
}
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
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}

	
}
