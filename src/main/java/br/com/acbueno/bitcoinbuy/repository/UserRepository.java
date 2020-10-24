package br.com.acbueno.bitcoinbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acbueno.bitcoinbuy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
   User findByCpf(String cpf);

}
