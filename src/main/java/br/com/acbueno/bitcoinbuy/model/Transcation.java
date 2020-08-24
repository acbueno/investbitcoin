package br.com.acbueno.bitcoinbuy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction")
public class Transcation {

  @Id
  String id;

  String cpf;

  float shopBTC;

  float expenseBTC;

  float currecyBTC;
  
  public Transcation() {
  }

  public Transcation(String cpf, float shopBTC, float expenseBTC, float currecyBTC) {
    this.cpf = cpf;
    this.shopBTC = shopBTC;
    this.expenseBTC = expenseBTC;
    this.currecyBTC = currecyBTC;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public float getShopBTC() {
    return shopBTC;
  }

  public void setShopBTC(float shopBTC) {
    this.shopBTC = shopBTC;
  }

  public float getExpenseBTC() {
    return expenseBTC;
  }

  public void setExpenseBTC(float expenseBTC) {
    this.expenseBTC = expenseBTC;
  }

  public float getCurrecyBTC() {
    return currecyBTC;
  }

  public void setCurrecyBTC(float currecyBTC) {
    this.currecyBTC = currecyBTC;
  }

  @Override
  public String toString() {
    return "Transcation [id=" + id + ", cpf=" + cpf + ", shopBTC=" + shopBTC + ", expenseBTC="
        + expenseBTC + ", currecyBTC=" + currecyBTC + "]";
  }  

}
