package br.com.acbueno.bitcoinbuy.dto;

import br.com.acbueno.bitcoinbuy.model.UserBalance;

public class InvestmentAccountDtoOutput {

  private Float amountBRL;
  private Float amountBTC;

  public Float getAmountBRL() {
    return amountBRL;
  }

  public void setAmountBRL(Float amountBRL) {
    this.amountBRL = amountBRL;
  }

  public Float getAmountBTC() {
    return amountBTC;
  }

  public void setAmountBTC(Float amountBTC) {
    this.amountBTC = amountBTC;
  }

  public UserBalance transformaParaObjeto() {
    return new UserBalance(amountBRL, amountBTC);
  }

  @Override
  public String toString() {
    return "InvestmentAccountDtoOutput [amountBRL=" + amountBRL + ", amountBTC=" + amountBTC + "]";
  }

}
