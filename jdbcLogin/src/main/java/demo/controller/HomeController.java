package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/admin")
	public void admin() {}
	
	
	// 2.1 로그인 컨트롤러 연결할 jsp 경로 설정하고 jsp 페이지 만들자
	@RequestMapping("/login")
	public void login() {}
	
}
