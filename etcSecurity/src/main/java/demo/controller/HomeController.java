package demo.controller;

import demo.service.HelloMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.domain.Account;
import demo.repository.AccountRepository;
import demo.security.UserDetailsImpl;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Account account){
		// 회원정보 데이터베이스에 저장
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
		
		// SecurityContextHolder에서 Context를 받아 인증 설정
		UserDetailsImpl userDetails = new UserDetailsImpl(account);
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping("/getPrivateMessage")
	@PreAuthorize("(#account.userid == principal.Username) or hasRole('ROLE_ADMIN')")
	public String authstring(Account account, Model model) {
		model.addAttribute("msg", "당신은 관리자거나 요청 파라미터랑 아이디가 같습니다?");
		return "authorizedMessage";
	}

	@RequestMapping("/getUserMessage")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String userMesasge(Account account, Model model){
		model.addAttribute("msg", "당신은 한낱 유저입니다. ㅠ");
		return "authorizedMessage";
	}

	@RequestMapping("/403")
	public void accessdeniedPage(){}

	@RequestMapping("/userinformation")
	public void userinformation(Model model){
		model.addAttribute("user", accountRepository.findMe());
	}

	@Autowired
	HelloMessageService helloMessageService;
	@RequestMapping("/message")
	@ResponseBody
	public String  getMessage(){
		return helloMessageService.getMessage();
	}
}
