package br.com.acbueno.bitcoinbuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.acbueno.bitcoinbuy.dto.AmountDTO;
import br.com.acbueno.bitcoinbuy.dto.BalanceDTO;
import br.com.acbueno.bitcoinbuy.dto.InvestmentAccountDtoOutput;
import br.com.acbueno.bitcoinbuy.dto.TranscationDTO;
import br.com.acbueno.bitcoinbuy.dto.UserDTO;
import br.com.acbueno.bitcoinbuy.dto.shopBitCoinInputDTO;
import br.com.acbueno.bitcoinbuy.model.Transcation;
import br.com.acbueno.bitcoinbuy.model.UserBalance;
import br.com.acbueno.bitcoinbuy.repository.OpertationRepository;
import br.com.acbueno.bitcoinbuy.repository.TranscationRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/user")
public class OperationController {


  @Autowired
  OpertationRepository userRepository;

  @Autowired
  TranscationRepository transcationRepository;

  Logger logger = Logger.getLogger(OperationController.class);


  @PostMapping("/createUser")
  public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
    try {
      Optional<UserBalance> userDate = Optional.ofNullable(userRepository.findByCpf(dto.getCpf()));
      if (userDate.isPresent()) {
        return new ResponseEntity<>("CPF ja cadastrado", HttpStatus.CREATED);
      } else {
        userRepository.save(dto.transformaParaObjeto());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping("/creditBRL/{cpf}")
  public ResponseEntity<?> creditBalance(@PathVariable("cpf") String cpf,
      @RequestBody BalanceDTO dtoBalance) {

    Optional<UserBalance> balanceUpdate = Optional.ofNullable(userRepository.findByCpf(cpf));

    if (balanceUpdate.isPresent()) {
      UserBalance updateUserBalance = balanceUpdate.get();
      dtoBalance.setCpf(balanceUpdate.get().getCpf());
      updateUserBalance.setBalanceBRLCredit(dtoBalance.getBalanceBRL());
      userRepository.save(updateUserBalance);

      return new ResponseEntity<>(dtoBalance, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/shopBitCoin/{cpf}")
  public ResponseEntity<?> buyBitcoint(@PathVariable("cpf") String cpf,
      @RequestBody shopBitCoinInputDTO dto) throws JsonMappingException, JsonProcessingException {

    InvestmentAccountDtoOutput investmentAccountDtoOutput = new InvestmentAccountDtoOutput();

    TranscationDTO transcationDTO = new TranscationDTO();

    boolean OperationOk = false;

    Optional<UserBalance> balanceUpdate = Optional.ofNullable(userRepository.findByCpf(cpf));

    if (balanceUpdate.isPresent()) {

      UserBalance updateUserBalance = balanceUpdate.get();

      RestTemplate restTemplate = new RestTemplate();
      UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
          .host("api.coinbase.com").path("v2/prices/spot").queryParam("currency", "BRL").build();

      ResponseEntity<String> entityBitCoin =
          restTemplate.getForEntity(uri.toString(), String.class);

      ObjectMapper mapper = new ObjectMapper();
      JsonNode actualObj = mapper.readTree(entityBitCoin.getBody());
      String currencyBTC = actualObj.get("data").get("amount").toString();
      actualObj.get("data").get("amount").toString();



      String valueTranformationBTC[] = currencyBTC.split("\"");
      String valueBT = valueTranformationBTC[1].substring(0, 2);

      Float currencyBitCoin = Float.parseFloat(valueBT);

      if (calcuteBitCoin(dto.getAmountBitcoin(), balanceUpdate.get().getBalanceBRL(),
          currencyBitCoin)) {

        float expenseBTC = (currencyBitCoin * dto.getAmountBitcoin());
        float totalBRL = balanceUpdate.get().getBalanceBRL() - expenseBTC;
        float totalBitCoin = dto.getAmountBitcoin() + balanceUpdate.get().getBalanceBitcoin();
        dto.setAmountBitcoin(totalBitCoin);
        updateUserBalance.setBalanceBRL(totalBRL);
        updateUserBalance.setBalanceBitcoin(totalBitCoin);

        userRepository.save(updateUserBalance);

        investmentAccountDtoOutput.setAmountBRL(totalBRL);
        investmentAccountDtoOutput.setAmountBTC(totalBitCoin);
        transcationDTO.setCurrecyBTC(currencyBitCoin);
        transcationDTO.setExpenseBTC(expenseBTC);
        transcationDTO.setShopBTC(dto.getAmountBitcoin());
        transcationDTO.setCpf(cpf);


        logger.info("Value transctation: " + transcationDTO.toString());

        transcationRepository.insert(transcationDTO.transformaParaObjeto());

        OperationOk = true;

      } else {

        OperationOk = false;
      }

    }
    if (OperationOk) {
      return new ResponseEntity<>(investmentAccountDtoOutput, HttpStatus.OK);
    } else {

      return new ResponseEntity<String>("Valor insuficiente para investimento",
          HttpStatus.NOT_FOUND);
    }

  }

  private boolean calcuteBitCoin(float shopCoin, float amountBRL, float currencyBitCoin) {

    float totalCost = shopCoin * currencyBitCoin;

    if (amountBRL >= totalCost) {
      return true;
    } else {
      return false;
    }

  }

  @GetMapping("/amounts/{cpf}")
  public ResponseEntity<?> getAmountValues(@PathVariable("cpf") String cpf)
      throws JsonMappingException, JsonProcessingException {

    Optional<UserBalance> balannceDta = Optional.of(userRepository.findByCpf(cpf));
    AmountDTO amountDTO = new AmountDTO();

    if (balannceDta.isPresent()) {
      UserBalance data = balannceDta.get();

      RestTemplate restTemplate = new RestTemplate();

      UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
          .host("api.coinbase.com").path("v2/prices/spot").queryParam("currency", "BRL").build();

      ResponseEntity<String> entityBitCoin =
          restTemplate.getForEntity(uri.toString(), String.class);

      ObjectMapper mapper = new ObjectMapper();
      JsonNode actualObj = mapper.readTree(entityBitCoin.getBody());
      String currencyBTC = actualObj.get("data").get("amount").toString();
      actualObj.get("data").get("amount").toString();



      String valueTranformationBTC[] = currencyBTC.split("\"");
      String valueBT = valueTranformationBTC[1].substring(0, 2);
      Float currencyBitCoin = Float.parseFloat(valueBT);

      amountDTO.setAmountBRLCurrency(data.getBalanceBitcoin() * currencyBitCoin);
      amountDTO.setAmountBTCCurrency(data.getBalanceBitcoin());

      List<Transcation> listTrasTranscations = transcationRepository.findByCpf(cpf);

      float sumShopBTC = 0;
      for (Transcation transcation : listTrasTranscations) {
        sumShopBTC += transcation.getShopBTC();
      }

      amountDTO.setAmountInvestmentBRL(sumShopBTC);
      float profit = amountDTO.getAmountBRLCurrency() * currencyBitCoin - sumShopBTC;
      amountDTO.setProfit(profit);
      amountDTO.setBtcCurrency(currencyBitCoin);


      List<Transcation> historyTranscations = transcationRepository.findByCpf(cpf);
      for (Transcation transcation : historyTranscations) {
        Map<String, String> mapTranstraion = new HashMap<String, String>();
        mapTranstraion.put("v1", String.valueOf(transcation.getShopBTC()));
        mapTranstraion.put("v2", String.valueOf(transcation.getCurrecyBTC()));
        amountDTO.setMapTransction(mapTranstraion);
      }



      return new ResponseEntity<>(amountDTO, HttpStatus.OK);

    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


}
