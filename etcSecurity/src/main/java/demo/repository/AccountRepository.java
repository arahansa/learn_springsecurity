package demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import demo.domain.Account;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByUserid(String userid);

	@Query("select a from Account a where a.userid = ?#{ principal.Username }")
	Account findMe();

}
