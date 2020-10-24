package br.com.acbueno.bitcoinbuy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sun.el.parser.ParseException;
import br.com.acbueno.bitcoinbuy.dto.InvestmentAccountDtoOutput;
import br.com.acbueno.bitcoinbuy.model.Account;
import br.com.acbueno.bitcoinbuy.service.AccountService;
import br.com.acbueno.bitcoinbuy.service.TranscationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/account")
@Api(value = "Account")
public class AccountController {

  @Autowired
  AccountService accountService;
  
  @Autowired
  TranscationService transcationService;
  
  Logger logger = LoggerFactory.getLogger(AccountController.class);
  
  @ApiOperation( value = "Adciona credito na Moeda R$ na conta de investimento")
  @ApiParam(value = "cpf")
  @PutMapping("/addcredit/{cpf}")
  public ResponseEntity<Account> updateCredit(@PathVariable("cpf") String cpf,
      @RequestBody Account account) {
    try {
      Account accountUpdate = accountService.addCredit(account, cpf);
      return ResponseEntity.ok(accountUpdate);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
  
  @ApiOperation(value = "Realiza compra de BITCOIN")
  @ApiParam(value = "cpf")
  @PutMapping("/buybtc/{cpf}")
  public ResponseEntity<String> updateBitCoin(@PathVariable("cpf") String cpf,
      @RequestBody Account account) {
    String accountUpdate = accountService.buyBitCoin(account, cpf);
    return ResponseEntity.ok(accountUpdate);
  }


  private InvestmentAccountDtoOutput convertToDTO(Account account) throws ParseException {
    
    InvestmentAccountDtoOutput investmentAccountDtoOutput = new InvestmentAccountDtoOutput();
    investmentAccountDtoOutput.setSaldBTC(account.getBitcoint());;
    investmentAccountDtoOutput.setSaldBRL(account.getCredit());
    investmentAccountDtoOutput.setCurrencyBTC(accountService.getCurrencyQuote());
    if(account.getBitcoint()==0) {
      investmentAccountDtoOutput.setInvestmentBRL(0F);
      investmentAccountDtoOutput.setProfit(0F);
    } else {
      investmentAccountDtoOutput.setInvestmentBRL(transcationService.getInvestment(account.getUser()));
      investmentAccountDtoOutput.setProfit((account.getBitcoint()*accountService.getCurrencyQuote()) - investmentAccountDtoOutput.getInvestmentBRL());
      if(investmentAccountDtoOutput.getProfit()<0) {
        investmentAccountDtoOutput.setProfit(0F);
      }
    }
    
    logger.info("Total Currency Bitcoint: " + account.getBitcoint()*accountService.getCurrencyQuote());
    float valueTest = account.getBitcoint()*accountService.getCurrencyQuote();
    float valueTest2 =  investmentAccountDtoOutput.getInvestmentBRL();
    float valueTest3 = valueTest - valueTest2;
    logger.info("Currency BTC Today: " + account.getBitcoint());
    logger.info("BTC " + valueTest2);
    logger.info("Value invesment " + valueTest3);
    logger.info("Profit: " + "|" + valueTest3 );
 
    investmentAccountDtoOutput.setTranscation(transcationService.listLastTranscation(account.getUser().getCpf()));
    return  investmentAccountDtoOutput;
  }
  
  @ApiOperation(value = "Retorna saldo em reais.\n"
      + "Retorna saldo em Bitcoint.\n"
      + "Retorna Valor Investido.\n"
      + "Retorna Lucro Obtido.\n"
      + "Retorna Cotação atual do BITCOIN para modea REAL.\n"
      + "Retorna histórico das últimas trasanações.")
  @ApiParam(value = "cpf")
  @GetMapping("/getinfo/{cpf}")
  public InvestmentAccountDtoOutput getGeneralInformation(@PathVariable("cpf") String cpf) throws ParseException{     
    return convertToDTO(accountService.getAccountInfo(cpf));
  }


}
