package br.com.acbueno.bitcoinbuy.dto;

import java.util.Map;

public class AmountDTO {
  
  private float amountBRLCurrency;
  
  private float amountBTCCurrency;
  
  private float amountInvestmentBRL;

  private float profit;
  
  private float btcCurrency;
  
  private static Map<String, String> mapTransction;
  

  public float getAmountBRLCurrency() {
    return amountBRLCurrency;
  }

  public void setAmountBRLCurrency(float amountBRLCurrency) {
    this.amountBRLCurrency = amountBRLCurrency;
  }

  public float getAmountBTCCurrency() {
    return amountBTCCurrency;
  }

  public void setAmountBTCCurrency(float amountBTCCurrency) {
    this.amountBTCCurrency = amountBTCCurrency;
  }

  public float getAmountInvestmentBRL() {
    return amountInvestmentBRL;
  }

  public void setAmountInvestmentBRL(float amountInvestmentBRL) {
    this.amountInvestmentBRL = amountInvestmentBRL;
  }

  public float getProfit() {
    return profit;
  }

  public void setProfit(float profit) {
    this.profit = profit;
  }

  public float getBtcCurrency() {
    return btcCurrency;
  }

  public void setBtcCurrency(float btcCurrency) {
    this.btcCurrency = btcCurrency;
  }

  public static Map<String, String> getMapTransction() {
    return mapTransction;
  }

  public static void setMapTransction(Map<String, String> mapTransction) {
    AmountDTO.mapTransction = mapTransction;
  }
    
}
