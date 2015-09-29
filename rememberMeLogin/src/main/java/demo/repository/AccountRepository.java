package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	public Account findByUserid(String userid);
}
