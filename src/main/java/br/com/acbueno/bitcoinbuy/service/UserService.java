package br.com.acbueno.bitcoinbuy.service;

import br.com.acbueno.bitcoinbuy.model.User;

public interface UserService {
  
  User createUser(User user);
  
  User findByCPF(String cpf); 

}
