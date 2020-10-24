package br.com.acbueno.bitcoinbuy.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.bitcoinbuy.enums.Operator;
import br.com.acbueno.bitcoinbuy.model.Transcation;
import br.com.acbueno.bitcoinbuy.model.User;
import br.com.acbueno.bitcoinbuy.repository.TranscationRepository;
import br.com.acbueno.bitcoinbuy.repository.UserRepository;
import br.com.acbueno.bitcoinbuy.service.TranscationService;

@Service
public class TranscationServiceImpl implements TranscationService {

  @Autowired
  TranscationRepository trascationRepository;

  @Autowired
  UserRepository userRepository;

  List<Double> listBitcoin = new ArrayList<>();

  Logger logger = LoggerFactory.getLogger(TranscationServiceImpl.class);

  public Float getInvestment(User user) {

    List<Transcation> transcation = trascationRepository.findByOperator(Operator.BUY);
    for (Transcation data : transcation) {
      Double values = Double.valueOf(data.getDebit());
      listBitcoin.add(values);
    }
    Double valueInvestemtBRL = listBitcoin.stream().mapToDouble(Double::doubleValue).sum();
    Float valueToFloat = Float.valueOf(valueInvestemtBRL.toString());

    return valueToFloat;
  }

  private List<Transcation> listTranscationByUser(String cpf) {
    User user = userRepository.findByCpf(cpf);
    List<Transcation> transcations = trascationRepository.findByUser(user);
    return transcations;
  }


  public List<Transcation> listLastTranscation(String cpf) {
    List<Transcation> fiveLastTranscation = new ArrayList<>();
    List<Transcation> listLastTranscation = listTranscationByUser(cpf);
    Collections.reverse(listLastTranscation);

    int numberTranscation = listLastTranscation.size();

    if (numberTranscation == 0) {
      fiveLastTranscation.isEmpty();
    } else if (numberTranscation == 1) {
      fiveLastTranscation.add(listLastTranscation.get(0));
      logger.info("Value add List fiveLastTranscation: " + listLastTranscation.get(0).toString());
    } else if (numberTranscation < 5 && numberTranscation > 0) {
      numberTranscation = numberTranscation - 1;
      for (int i = 0; i <= numberTranscation; i++) {
        fiveLastTranscation.add(listLastTranscation.get(i));
        logger.info("Value add List fiveLastTranscation: " + listLastTranscation.get(i).toString());
      }
    } else {
      for (int i = 0; i <= 5; i++) {
        fiveLastTranscation.add(listLastTranscation.get(i));
        logger.info("Value add List fiveLastTranscation: " + listLastTranscation.get(i).toString());
      }
    }

    return fiveLastTranscation;
  }



}
