package br.com.acbueno.bitcoinbuy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.acbueno.bitcoinbuy.model.UserBalance;

public interface OpertationRepository extends MongoRepository<UserBalance, String> {
  
  UserBalance findByCpf(String cpf);

}
