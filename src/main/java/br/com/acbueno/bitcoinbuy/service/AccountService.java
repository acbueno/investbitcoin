package br.com.acbueno.bitcoinbuy.service;

import br.com.acbueno.bitcoinbuy.model.Account;

public interface AccountService {
  

  Account addCredit(Account account, String cpf);
  
  String buyBitCoin(Account account, String cpf);

  Float getCurrencyQuote();
  
  Account getAccountInfo(String cpf);
  

}
