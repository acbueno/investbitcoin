package br.com.acbueno.bitcoinbuy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {


  @Id
  @GeneratedValue( strategy=GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;

  @Column(name = "credit")
  Float credit;

  @Column(name = "bitcoint")
  Float bitcoint;


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  public Float getCredit() {
    return credit;
  }

  public void setCredit(Float credit) {
    this.credit = credit;
  }

  public Float getBitcoint() {
    return bitcoint;
  }

  public void setBitcoint(Float bitcoint) {
    this.bitcoint = bitcoint;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public Account() {
  }

  public Account(Float credit, Float bitcoint, User user) {
    this.credit = credit;
    this.bitcoint = bitcoint;
    this.user = user;
  } 

}
