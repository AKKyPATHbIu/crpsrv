package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_TIMESTAMP;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoPriceParserTimestampTest {

    @InjectMocks
    CryptoPriceParserTimestamp cryptoPriceParserTimestamp;

    @Test
    void parse() {
        var timestamp = "1641009600000";

        var expectedDate = LocalDateTime.of(2022, 1, 1, 6, 0);
        var expectedCryptoPrice = CryptoPriceDto.builder()
                .timestamp(expectedDate)
                .build();

        var cryptoPriceBuilder = CryptoPriceDto.builder();

        var actualBuilder = cryptoPriceParserTimestamp.parse(timestamp, cryptoPriceBuilder);

        var actualCryptoPrice = actualBuilder.build();

        assertThat(actualCryptoPrice).isEqualTo(expectedCryptoPrice);
    }

    @Test
    void getColumnName() {
        assertThat(cryptoPriceParserTimestamp.getColumnName()).isEqualTo(COLUMN_TIMESTAMP);
    }
}