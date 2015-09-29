package demo;

import demo.domain.Account;
import demo.repository.AccountRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RememberMeLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(RememberMeLoginApplication.class, args);
    }
    
  @Autowired AccountRepository accountRepository;
    
    // 초기 시작시 데이터 초기화 (JPA : CREATE-DROP )
    // InitializingBean 참고 : 토비의 봄 - (1.5) 커맨드 라인 스프링 앱 만들기
    // https://www.youtube.com/watch?v=dnCf2-XYXL8 
    @Bean
    InitializingBean insertFixtureUsers(){
		return ()->{
			Account admin = new Account();
			admin.setUserid("admin");
			admin.setPassword("1234");
			admin.setRole("ROLE_ADMIN");
			admin.setNick("관리자한사");
			accountRepository.save(admin);
			
			Account user = new Account();
			user.setUserid("arahansa");
			user.setPassword("1234");
			user.setRole("ROLE_USER");
			user.setNick("아라한사");
			accountRepository.save(user);


		};
	}


}
