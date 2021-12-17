package com.example.stocks.service.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter @Setter
public class StockDTO {

    private String name;
    private BigInteger currentPrice;
}
