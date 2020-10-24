package br.com.acbueno.bitcoinbuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acbueno.bitcoinbuy.enums.Operator;
import br.com.acbueno.bitcoinbuy.model.Account;
import br.com.acbueno.bitcoinbuy.model.Transcation;
import br.com.acbueno.bitcoinbuy.model.User;

public interface TranscationRepository extends JpaRepository<Transcation, Long> {
  
  List<Transcation> findByUser(User user);
  
  List<Transcation> findByOperator(Operator operator);
  
  
   

}
