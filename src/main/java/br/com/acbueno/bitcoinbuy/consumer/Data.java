package br.com.acbueno.bitcoinbuy.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

  String base;

  String currency;

  String amount;

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public Data() {

  }

  @Override
  public String toString() {
    return "Data [base=" + base + ", currency=" + currency + ", amount=" + amount + "]";
  }

}
