package demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique=true)
	private String userid;

	private String password;

	// 이 부분은 나중에 enum 과 일대다로 빼든 지 하는 작업이 필요할 것으로 보임. 
	// 시큐리티 튜토리얼이므로 간략하게 적습니다. 
	private String role;

	private String nick;


	// -------------------------------
	
	public Account() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userid=" + userid + ", password=" + password + ", role=" + role + ", nick="
				+ nick + "]";
	}

}
