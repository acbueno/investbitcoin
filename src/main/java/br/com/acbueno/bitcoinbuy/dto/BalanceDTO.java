package br.com.acbueno.bitcoinbuy.dto;

import br.com.acbueno.bitcoinbuy.model.UserBalance;

public class BalanceDTO {

  private String cpf;

  private float balanceBRL;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public float getBalanceBRL() {
    return balanceBRL;
  }

  public void setBalanceBRL(float balanceBRL) {
    this.balanceBRL =balanceBRL;
  }

  public UserBalance transformaParaObjeto() {
    return new UserBalance(balanceBRL);
  }

}
