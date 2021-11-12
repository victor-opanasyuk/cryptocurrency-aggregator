package com.example.cryptocurrencyaggregator.coin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Builder
@Table(name = "coin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_seq")
    @SequenceGenerator(name = "coin_seq", sequenceName = "coin_seq", allocationSize = 1)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "ticker")
    private String ticker;
}
