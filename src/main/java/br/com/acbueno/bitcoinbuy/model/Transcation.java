package br.com.acbueno.bitcoinbuy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.acbueno.bitcoinbuy.enums.Operator;

@Entity
@Table(name = "transcation")
public class Transcation {

  @Id
  @GeneratedValue( strategy=GenerationType.IDENTITY)
  @Column(name = "transcation_id")
  private Long id;

  @Column(name = "credit")
  private Float credit;

  @Column(name = "bitcoin")
  private Float bitcoin;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  private Operator operator;
  
  @Column(name = "debit")
  private Float debit;

  public Float getCredit() {
    return credit;
  }

  public void setCredit(Float credit) {
    this.credit = credit;
  }

  public Float getBitcoin() {
    return bitcoin;
  }

  public void setBitcoin(Float bitcoin) {
    this.bitcoin = bitcoin;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public Long getId() {
    return id;
  }
  
  

  public Float getDebit() {
    return debit;
  }

  public void setDebit(Float debit) {
    this.debit = debit;
  }

  public Transcation() {}

  public Transcation(Float credit, Float bitcoin, User user, Operator operator, Float debit) {
    this.credit = credit;
    this.bitcoin = bitcoin;
    this.user = user;
    this.operator = operator;
    this.debit = debit;
  }

  @Override
  public String toString() {
    return "Transcation [id=" + id + ", credit=" + credit + ", bitcoin=" + bitcoin + ", user="
        + user + ", operator=" + operator + "]";
  }

}
