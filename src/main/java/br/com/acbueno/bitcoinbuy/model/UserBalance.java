package br.com.acbueno.bitcoinbuy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userBalance")
public class UserBalance {

  @Id
  String id;

  String nome;

  String cpf;
  
  float balanceBRL;
  
  float balanceBitcoin;
  
  public UserBalance() {
    
  }
  
  
  public UserBalance(float balanceBRL) {
    this.balanceBRL = balanceBRL;
    
  }
  
  
  public UserBalance(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }
  
  public UserBalance(float balanceBRL, float balanceBitCoin) {
    this.balanceBRL = balanceBRL;
    this.balanceBitcoin = balanceBitCoin;
  }
  
  public UserBalance(String nome, String cpf, float balanceBRL, float balanceBitcoin) {
    this.nome = nome;
    this.cpf = cpf;
    this.balanceBRL = balanceBRL;
    this.balanceBitcoin = balanceBitcoin;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public float getBalanceBRL() {
    return balanceBRL;
  }

  public void setBalanceBRLCredit(float balanceBRL) {
    this.balanceBRL += balanceBRL;
  }
  
  public void setBalanceBRLDebit(float balanceBRL) {
    this.balanceBRL -=balanceBRL;
  }
  
  
  public void setBalanceBRL(float balanceBRL) {
    this.balanceBRL = balanceBRL;
  }
  

  public float getBalanceBitcoin() {
    return balanceBitcoin;
  }

  public void setBalanceBitcoin(float totalBitCoin) {
    this.balanceBitcoin = totalBitCoin;
  }

  @Override
  public String toString() {
    return "UserBalance [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", balanceBRL="
        + balanceBRL + ", balanceBitcoin=" + balanceBitcoin + "]";
  }

}
