package poly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@Column(name="Username")
	@NotBlank(message = "Username not allow null")
	private String username;
	
	@Column(name="Password")
	@NotBlank(message = "Password not allow null")
	private String password;
	
	@Column(name="Fullname")
	@NotBlank(message = "Fullname not allow null")
	private String fullname;

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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Users() {}
	public Users(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
	
}
