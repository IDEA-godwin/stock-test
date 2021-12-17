package com.example.stocks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "stocks")
@Getter @Setter
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigInteger currentPrice;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.LAZY)
    private Date createDate = new Date();

    @Temporal(value = TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.LAZY)
    private Date lastUpdate;

    public Stock(String name, BigInteger currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }
}
