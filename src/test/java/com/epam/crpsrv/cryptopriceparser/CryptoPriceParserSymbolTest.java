package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_SYMBOL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoPriceParserSymbolTest {

    @InjectMocks
    CryptoPriceParserSymbol cryptoPriceParserSymbol;

    @Test
    void parse() {
        var symbol = "BTC";

        var expectedCryptoPrice = CryptoPriceDto.builder()
                .symbol(symbol)
                .build();

        var cryptoPriceBuilder = CryptoPriceDto.builder();

        var actualBuilder = cryptoPriceParserSymbol.parse(symbol, cryptoPriceBuilder);
        var actualCryptoPrice = actualBuilder.build();

        assertThat(actualCryptoPrice).isEqualTo(expectedCryptoPrice);
    }

    @Test
    void getColumnName() {
        Assertions.assertThat(cryptoPriceParserSymbol.getColumnName()).isEqualTo(COLUMN_SYMBOL);
    }
}