package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcLoginApplication {
	
	// 이 프로젝트는 이미 데이터베이스에 유저 정보 테이블이 있다는 것을 전제로 합니다.
    public static void main(String[] args) {
        SpringApplication.run(JdbcLoginApplication.class, args);
    }
}
