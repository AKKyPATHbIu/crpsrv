package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoPriceParserPriceTest {

    @InjectMocks
    CryptoPriceParserPrice cryptoPriceParserPrice;

    @Test
    void parse() {
        var price = "125.20";

        var expectedCryptoPrice = CryptoPriceDto.builder()
                .price(new BigDecimal(price))
                .build();

        var cryptoPriceBuilder = CryptoPriceDto.builder();

        var actualBuilder = cryptoPriceParserPrice.parse(price, cryptoPriceBuilder);

        var actualCryptoPrice = actualBuilder.build();

        assertThat(actualCryptoPrice).isEqualTo(expectedCryptoPrice);
    }

    @Test
    void getColumnName() {
        assertThat(cryptoPriceParserPrice.getColumnName()).isEqualTo(COLUMN_PRICE);
    }
}