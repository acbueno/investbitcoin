package br.com.acbueno.bitcoinbuy.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

  private Data data;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public Quote() {}

  @Override
  public String toString() {
    return "Quote [data=" + data + "]";
  }

}
