package com.example.cryptocurrencyaggregator.coin.service;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import com.example.cryptocurrencyaggregator.coin.repository.CoinRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoinServiceUnitTest {

    private CoinService coinService;

    @Mock
    CoinRepository coinRepository;

    @BeforeEach
    void init() {
        coinService = new CoinServiceImpl(coinRepository);
    }

    @Test
    void shouldSaveCoin() {
        // given
        Coin coin = Coin.builder()
                .id(1)
                .title("Bitcoin")
                .ticker("BTC")
                .build();

        // when
        coinService.saveCoin(coin);

        // then
        verify(coinRepository, times(1)).saveAndFlush(coin);
    }

    @Test
    void shouldGetAllCoins() {
        // given
        List<Coin> coins = Arrays.asList(
                Coin.builder().build(),
                Coin.builder().build(),
                Coin.builder().build()
        );

        // when
        when(coinRepository.findAll()).thenReturn(coins);

        // then
        Assertions.assertThat(coinService.getAllCoins().equals(coins)).isTrue();
    }

    @Test
    void shouldGetCoinById() {
        // given
        Coin coin = Coin.builder().id(1).build();

        // when
        when(coinRepository.findById(1)).thenReturn(Optional.of(coin));

        // then
        Coin coinById = coinService.getCoinById(1);
        Assertions.assertThat(coinById.equals(coin)).isTrue();
    }

    @Test
    void coinMayBeDeleted() {
        // given
        Coin coinToDelete = Coin.builder().id(1).build();
        when(coinRepository.findById(1)).thenReturn(Optional.of(coinToDelete));

        // when
        coinService.deleteCoin(1);

        // then
        verify(coinRepository, times(1)).delete(coinToDelete);
    }

    @Test
    void coinMayBeUpdated() {
        // given
        Coin visitToUpdate = Coin.builder().id(1).build();

        // when
        coinService.updateCoin(visitToUpdate);

        // then
        verify(coinRepository, times(1)).save(visitToUpdate);
    }

    @Test
    void shouldThrownResourceNotFoundIfThereIsNoVisitById() {
        // given
        when(coinRepository.findById(1)).thenReturn(Optional.empty());

        // expect
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> coinService.getCoinById(1));

        assertThrows(
                ResourceNotFoundException.class,
                () -> coinService.getCoinById(1)
        );
    }
}
