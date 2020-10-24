package br.com.acbueno.bitcoinbuy.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.acbueno.bitcoinbuy.model.Account;
import br.com.acbueno.bitcoinbuy.model.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
  
  Account findByUser(User user);
  
  @Query("SELECT account FROM Account account JOIN User user on user.user.id = account.user.id")
  public Account findByUserId(@Param("userId") Long userId);

}
