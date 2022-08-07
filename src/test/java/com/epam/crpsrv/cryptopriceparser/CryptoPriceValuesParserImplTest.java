package com.epam.crpsrv.cryptopriceparser;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import com.epam.crpsrv.cryptoprice.CryptoPriceParserConfig;
import com.epam.crpsrv.cryptoprice.CryptoPriceParserFactoryImpl;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderUnknown;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParser;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParserImpl;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CryptoPriceParserFactoryImpl.class, CryptoPriceValuesParserImpl.class,
        CryptoPriceDtoBuilderUnknown.class})
@Import(value = {CryptoPriceParserConfig.class})
class CryptoPriceValuesParserImplTest {

    private static final String BTC_VALUES =
            "  timestamp,   price,symbol   \n" +
                    " 1641009600000 , 46813.21,BTC  \n" +
                    "1641020400000,46979.61,BTC";

    @Autowired
    private CryptoPriceValuesParser cryptoPriceValuesParser;

    @Test
    void parse() {
        var expectedResult = List.of(
                CryptoPriceDto.builder()
                        .symbol("BTC")
                        .price(new BigDecimal("46813.21"))
                        .timestamp(LocalDateTime.of(2022, 1, 1, 6, 0))
                        .build(),
                CryptoPriceDto.builder()
                        .symbol("BTC")
                        .price(new BigDecimal("46979.61"))
                        .timestamp(LocalDateTime.of(2022, 1, 1, 9, 0))
                        .build()
        );

        var actualResult = cryptoPriceValuesParser.parse(BTC_VALUES.getBytes(StandardCharsets.UTF_8));

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}