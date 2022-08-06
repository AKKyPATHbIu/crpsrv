package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilder.COLUMN_TIMESTAMP;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderTimestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoPriceDtoBuilderTimestampTest {

    @InjectMocks
    CryptoPriceDtoBuilderTimestamp cryptoPriceParserTimestamp;

    @Test
    void parse() {
        var timestamp = "1641009600000";

        var expectedDate = LocalDateTime.of(2022, 1, 1, 6, 0);
        var expectedCryptoPrice = CryptoPriceDto.builder()
                .timestamp(expectedDate)
                .build();

        var cryptoPriceBuilder = CryptoPriceDto.builder();

        var actualBuilder = cryptoPriceParserTimestamp.build(timestamp, cryptoPriceBuilder);

        var actualCryptoPrice = actualBuilder.build();

        assertThat(actualCryptoPrice).isEqualTo(expectedCryptoPrice);
    }

    @Test
    void getColumnName() {
        assertThat(cryptoPriceParserTimestamp.getColumnName()).isEqualTo(COLUMN_TIMESTAMP);
    }
}