package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.domain.Account;
import demo.repository.AccountRepository;
import demo.security.UserDetailsImpl;

@Controller
public class HomeController {
	
	
	
	@RequestMapping(value = "/")
	public String index() {return "index";}
	
	@RequestMapping("/admin")
	public void admin() {}
	
	@RequestMapping("/user")
	public void user() {}
	
	@RequestMapping("/login")
	public void login() {}
	
	
	@RequestMapping("/registerForm")
	public void registerForm() {}
	
	
	// 필요한 부분은 Serivce계층으로 옮겨줘야 합니다. 
	@Autowired AccountRepository accountRepository;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Account account){
		// 회원정보 데이터베이스에 저장
		accountRepository.save(account);
		
		// SecurityContextHolder에서 Context를 받아 인증 설정
		UserDetailsImpl userDetails = new UserDetailsImpl(account);
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
}
