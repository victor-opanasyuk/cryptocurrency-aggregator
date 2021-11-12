package com.example.cryptocurrencyaggregator.coin.repository;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoinEntityTest {

    private static final Integer POSTGRES_PORT = 5432;
    private static final String URL_PATTERN = "jdbc:p6spy:postgresql://%s:%s/coins";
    private static final String TEST_USERNAME = "test_login";
    private static final String TEST_PASSWORD = "test_password";

    @Container
    private static PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6.1")
            .withUsername(TEST_USERNAME)
            .withPassword(TEST_PASSWORD)
            .withDatabaseName("coins")
            .withExposedPorts(POSTGRES_PORT);


    @DynamicPropertySource
    public static void overrideProp(DynamicPropertyRegistry propertyRegistry) {
        String url = String.format(URL_PATTERN, container.getContainerIpAddress(), container.getMappedPort(POSTGRES_PORT));

        propertyRegistry.add("spring.datasource.url", url::toString);
        propertyRegistry.add("spring.datasource.username", container::getUsername);
        propertyRegistry.add("spring.datasource.password", container::getPassword);
    }


    @Autowired
    CoinRepository coinRepository;

    @Test
    void getById() {
        Assertions.assertThat(coinRepository.getCoinById(1).getTicker()).isEqualToIgnoringCase("BTC");
    }

    @Test
    void getByTicker() {

        Coin ada = coinRepository.getCoinByTicker("ADA");

        Assertions.assertThat(ada.getTicker()).isEqualToIgnoringCase("ADA");
        Assertions.assertThat(ada.getId()).isEqualTo(2);
    }
}
