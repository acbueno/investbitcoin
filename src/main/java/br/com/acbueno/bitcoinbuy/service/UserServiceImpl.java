package br.com.acbueno.bitcoinbuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acbueno.bitcoinbuy.model.Account;
import br.com.acbueno.bitcoinbuy.model.User;
import br.com.acbueno.bitcoinbuy.repository.AccountRepository;
import br.com.acbueno.bitcoinbuy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  UserRepository userRepository;

  @Override
  public User createUser(User user) {
     User userData = userRepository.save(user);
    return userData;
  }

  @Override
  public User findByCPF(String cpf) {
     
    User userData = userRepository.findByCpf(cpf);
    
    return userData;
  }
  
  

}
