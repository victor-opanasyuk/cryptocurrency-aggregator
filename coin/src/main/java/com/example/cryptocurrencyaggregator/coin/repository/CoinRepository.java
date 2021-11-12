package com.example.cryptocurrencyaggregator.coin.repository;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Integer> {

    Coin getCoinById(Integer petId);

    Coin getCoinByTicker(String ticker);
}
