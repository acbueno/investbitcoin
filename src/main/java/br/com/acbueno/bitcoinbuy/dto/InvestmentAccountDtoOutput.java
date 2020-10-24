package br.com.acbueno.bitcoinbuy.dto;

import java.util.List;
import br.com.acbueno.bitcoinbuy.model.Transcation;

public class InvestmentAccountDtoOutput {

  private Float saldBRL;

  private Float saldBTC;

  private Float InvestmentBRL;

  private Float currencyBTC;

  private Float profit;
  
  private List<Transcation> transcation;


  public Float getSaldBRL() {
    return saldBRL;
  }

  public void setSaldBRL(Float saldBRL) {
    this.saldBRL = saldBRL;
  }

  public Float getSaldBTC() {
    return saldBTC;
  }

  public void setSaldBTC(Float saldBTC) {
    this.saldBTC = saldBTC;
  }

  public Float getInvestmentBRL() {
    return InvestmentBRL;
  }

  public void setInvestmentBRL(Float investmentBRL) {
    InvestmentBRL = investmentBRL;
  }

  public Float getCurrencyBTC() {
    return currencyBTC;
  }

  public void setCurrencyBTC(Float currencyBTC) {
    this.currencyBTC = currencyBTC;
  }



  public Float getProfit() {
    return profit;
  }

  public void setProfit(Float profit) {
    this.profit = profit;
  }



  public List<Transcation> getTranscation() {
    return transcation;
  }

  public void setTranscation(List<Transcation> transcation) {
    this.transcation = transcation;
  }

  @Override
  public String toString() {
    return "InvestmentAccountDtoOutput [saldBRL=" + saldBRL + ", saldBTC=" + saldBTC
        + ", InvestmentBRL=" + InvestmentBRL + ", currencyBTC=" + currencyBTC + ", profit=" + profit
        + ", transcation=" + transcation + "]";
  }

}
