package br.com.acbueno.bitcoinbuy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.acbueno.bitcoinbuy.consumer.QuoteBitCoin;

@SpringBootTest
class BitcoinbuyApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  void getApiExertenalBitcoin() {
    QuoteBitCoin quoteBitCoin = QuoteBitCoin.getInstance();
    String expected = "BRL";
    String actual = quoteBitCoin.getCurrency();
    assertThat(expected.equalsIgnoreCase(actual));

  }

}
