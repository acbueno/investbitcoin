package br.com.acbueno.bitcoinbuy.dto;

import br.com.acbueno.bitcoinbuy.model.UserBalance;

public class UserDTO {

  private String nome;
  private String cpf;

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

  public UserBalance transformaParaObjeto(){
    return new UserBalance(nome, cpf);
}

}
