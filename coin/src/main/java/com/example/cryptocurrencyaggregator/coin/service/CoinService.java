package com.example.cryptocurrencyaggregator.coin.service;

import com.example.cryptocurrencyaggregator.coin.model.Coin;

import java.util.List;

public interface CoinService {


    void saveCoin(Coin coin);

    List<Coin> getAllCoins();

    Coin getCoinById(int i);

    void deleteCoin(int i);

    void updateCoin(Coin coinToUpdate);
}
