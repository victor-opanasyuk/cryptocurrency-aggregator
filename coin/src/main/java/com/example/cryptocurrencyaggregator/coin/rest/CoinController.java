package com.example.cryptocurrencyaggregator.coin.rest;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import com.example.cryptocurrencyaggregator.coin.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinController {

    private final CoinService coinService;

    @GetMapping(value = "/coins/{coinId}")
    public ResponseEntity<Coin> getCoin(@PathVariable("coinId") Integer coinId) {
        return new ResponseEntity<>(coinService.getCoinById(coinId), HttpStatus.OK);
    }

    
    
    
}
