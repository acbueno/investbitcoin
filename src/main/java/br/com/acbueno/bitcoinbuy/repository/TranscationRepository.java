package br.com.acbueno.bitcoinbuy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.acbueno.bitcoinbuy.model.Transcation;

public interface TranscationRepository extends MongoRepository<Transcation, String> {
  
   List<Transcation> findByCpf(String cpf); 


}
