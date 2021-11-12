package com.example.cryptocurrencyaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootConfiguration
@SpringBootApplication(scanBasePackages = "com.example.cryptocurrencyaggregator")
public class CryptocurrencyAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyAggregatorApplication.class, args);
	}

}
