package demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import demo.domain.Account;

// 기선님의 아무고나 소스 참조함.
public class UserDetailsImpl extends User {
	
	private String nick;
	public String getNick() { return nick;}
	public void setNick(String nick) {this.nick = nick; }
	
	public UserDetailsImpl(Account account) {
		super(account.getUserid(), account.getPassword(), authorities(account));
		this.nick = account.getNick(); 
	}

	private static Collection<? extends GrantedAuthority> authorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 원래 일대다, 이늄을 하는 식으로 해서 처리를 해야 할 것같지만
		// 그 부분의 매핑은 책을 사서 보시기를 바랍니다.
		// 절대 제가 귀찮아서ㅋㅋㅋ이러는게 아닙니다. 
		// 최근에 배운것을 막 바로 적어 블로깅하기도 뭐해서요 ㅎㅎ
		authorities.add(new SimpleGrantedAuthority(account.getRole()));
		return authorities;
	}

	
	
	
}
