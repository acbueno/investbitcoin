package br.com.acbueno.bitcoinbuy.dto;

import br.com.acbueno.bitcoinbuy.model.Transcation;

public class TranscationDTO {
  
  private String cpf;
  private float shopBTC;
  private float expenseBTC;
  private float currecyBTC;
  
  
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
  
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public Transcation transformaParaObjeto(){
    return new Transcation(getCpf(), getShopBTC(), getExpenseBTC(), getCurrecyBTC());
}
  @Override
  public String toString() {
    return "TranscationDTO [cpf=" + cpf + ", shopBTC=" + shopBTC + ", expenseBTC=" + expenseBTC
        + ", currecyBTC=" + currecyBTC + "]";
  }

}
