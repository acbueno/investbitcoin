package br.com.acbueno.bitcoinbuy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long id;

  @NotBlank
  @Size(min = 3, max = 255)
  @Column(name = "name")
  private String name;

  @NotBlank
  @Column(name = "cpf")
  private String cpf;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Long getId() {
    return id;
  }

  public User() {}

  public User(@NotBlank @Size(min = 3, max = 255) String name, @NotBlank String cpf) {
    this.name = name;
    this.cpf = cpf;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
  }
}
