package br.com.acbueno.bitcoinbuy.consumer;

import java.text.DecimalFormat;

import org.springframework.web.client.RestTemplate;

public class QuoteBitCoin {

  private static QuoteBitCoin instance = null;
  
  

  private static final String URL_BITCOIN_QUOTE =
      "https://api.coinbase.com/v2/prices/spot?currency=BRL";
  
  
  
  private QuoteBitCoin() {
    
  }
  
  public static  QuoteBitCoin getInstance() {
    if(instance == null) {
      instance = new QuoteBitCoin();
    }
    return instance;
  }
  
  

  public String getAmount() {
    DecimalFormat df = new DecimalFormat("00");
    RestTemplate restTemplate = new RestTemplate();
    Quote quote = restTemplate.getForObject(URL_BITCOIN_QUOTE, Quote.class);
    Double valueConvert = Double.valueOf(quote.getData().getAmount());
    df.format(valueConvert);
    String valueConvertToString = String.valueOf(df.format(valueConvert));
    
    return valueConvertToString.substring(0,2);
  }
  
  public String getBase() {
    
    RestTemplate restTemplate = new RestTemplate();
    Quote quote = restTemplate.getForObject(URL_BITCOIN_QUOTE, Quote.class);
    
    return quote.getData().getBase();
  }
  
  public String getCurrency() {
    
    RestTemplate restTemplate = new RestTemplate();
    Quote quote = restTemplate.getForObject(URL_BITCOIN_QUOTE, Quote.class);

    return quote.getData().getCurrency();
    
  }

}
