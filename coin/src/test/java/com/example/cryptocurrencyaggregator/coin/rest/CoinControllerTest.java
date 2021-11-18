package com.example.cryptocurrencyaggregator.coin.rest;

import com.example.cryptocurrencyaggregator.coin.model.Coin;
import com.example.cryptocurrencyaggregator.coin.service.CoinService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(value = "spring")
@WebMvcTest(CoinController.class)
public class CoinControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CoinService coinService;

    @Test
    public void getCoin() throws Exception {
        // given
        Coin expectedVisit = Coin.builder()
                .id(1)
                .title("Bitcoin")
                .ticker("BTC")
                .build();
        
        Mockito.when(coinService.getCoinById(1)).thenReturn(expectedVisit);

        // expect
        mockMvc.perform(get("/coins/" + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Bitcoin"))
                .andExpect(jsonPath("$.ticker").value("BTC"));
    }
}
