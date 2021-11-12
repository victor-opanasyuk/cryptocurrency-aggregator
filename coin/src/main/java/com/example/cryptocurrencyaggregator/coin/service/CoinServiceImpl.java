package com.example.cryptocurrencyaggregator.coin.service;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import com.example.cryptocurrencyaggregator.coin.repository.CoinRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoinServiceImpl implements CoinService {

    private final CoinRepository repository;

    @Transactional
    @Override
    public void saveCoin(Coin coin) {
        repository.saveAndFlush(coin);
    }

    @Override
    public List<Coin> getAllCoins() {
        return repository.findAll();
    }

    @Override
    public Coin getCoinById(int i) {
        return repository.findById(i)
                .orElseThrow(() -> new ResourceNotFoundException("not found" + i));
    }

    @Transactional
    @Override
    public void deleteCoin(int i) {
        repository.delete(
                repository.findById(i)
                        .orElseThrow(() -> new ResourceNotFoundException("not found" + i))
        );
    }

    @Transactional
    @Override
    public void updateCoin(Coin coinToUpdate) {
        repository.save(coinToUpdate);
    }
}
