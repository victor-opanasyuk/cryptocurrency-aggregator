package com.example.cryptocurrencyaggregator.coin.model;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoinPojoTest {

    @Test
    void create_coin() {
        Coin coin = new Coin(1, "Bitcoin", "BTC");

        Assertions.assertThat(coin.getId()).isEqualTo(1);
        Assertions.assertThat(coin.getTitle()).isEqualToIgnoringCase("bitcoin");
        Assertions.assertThat(coin.getTicker()).isEqualToIgnoringCase("BTC");

    }
}
