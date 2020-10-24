package br.com.acbueno.bitcoinbuy.service;

import java.util.List;

import br.com.acbueno.bitcoinbuy.model.Transcation;
import br.com.acbueno.bitcoinbuy.model.User;

public interface TranscationService {
  
  Float getInvestment(User user);
  
  List<Transcation> listLastTranscation(String cpf);

}
