package br.com.acbueno.bitcoinbuy.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.bitcoinbuy.consumer.QuoteBitCoin;
import br.com.acbueno.bitcoinbuy.enums.Operator;
import br.com.acbueno.bitcoinbuy.model.Account;
import br.com.acbueno.bitcoinbuy.model.Transcation;
import br.com.acbueno.bitcoinbuy.model.User;
import br.com.acbueno.bitcoinbuy.repository.AccountRepository;
import br.com.acbueno.bitcoinbuy.repository.TranscationRepository;
import br.com.acbueno.bitcoinbuy.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  TranscationRepository trascationRepository;
  
  Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Override
  public Account addCredit(Account account, String cpf) {

    User user = userRepository.findByCpf(cpf);

    Optional<Account> accountUpdate = Optional.ofNullable(accountRepository.findByUser(user));

    if (!accountUpdate.isPresent()) {
      Account accountCreate = accountRepository.save(new Account(0F, 0F, user));

      return accountCreate;
    } else {
      accountUpdate.get()
          .setCredit(plusCredit(account.getCredit(), accountUpdate.get().getCredit()));
      accountRepository.save(accountUpdate.get());
      trascationRepository.save(new Transcation(account.getCredit(), 0F, user, Operator.CREDIT, 0F));
      return accountUpdate.get();
    }

  }

  private Float plusCredit(Float creditCurrecy, Float addCredit) {
    System.out.println("Valor Recebidos: " + creditCurrecy + " - " + addCredit);
    return creditCurrecy + addCredit;
  }

  
  public String buyBitCoin(Account account, String cpf) {

    User user = userRepository.findByCpf(cpf);
    Account accountUpdate = accountRepository.findByUser(user);

    if (accountUpdate != null) {
      if (makePurchase(accountUpdate, accountUpdate.getCredit(),
          getValueInvest(account.getBitcoint()))) {
       
        logger.info(accountUpdate.toString());
        Float totalBitcoin = plusCredit(accountUpdate.getBitcoint(), account.getBitcoint());
        accountUpdate.setBitcoint(totalBitcoin);
        accountRepository.save(accountUpdate);
        trascationRepository.save(new Transcation(0F, account.getBitcoint(), user, Operator.BUY,  getValueInvest(account.getBitcoint())));

        return "Compra BTC efetuada";
      } else {
        return "Valor insuficiente para compra de BTC";
      }

    } else {
      return "Conta investimento não encontrada";
    }

  }

  public Float getCurrencyQuote() {
    QuoteBitCoin quoteBitCoin = QuoteBitCoin.getInstance();
    return Float.valueOf(quoteBitCoin.getAmount());
  }

  private float getValueInvest(Float buyBTC) {
    Float investValue = getCurrencyQuote() * buyBTC;
    return investValue;
  }

  private boolean makePurchase(Account account, Float sald, Float valueInvest) {
    if (sald > valueInvest) {
      logger.info("Value sald: " + sald);
      logger.info("Value Invest: " + valueInvest);
      Float valueDebit = debit(account, valueInvest);
      logger.info("Value debit: " + valueDebit);
      account.setCredit(valueDebit);
     
      return true;
    } else {
      return false;
    }
  }

  private Float debit(Account account, Float valueInvest) {
    return  account.getCredit() - valueInvest;
  }
  
  
  public Account getAccountInfo(String cpf) {
    User user = userRepository.findByCpf(cpf);
    Account account = accountRepository.findByUser(user);
    return account;
  }

}
