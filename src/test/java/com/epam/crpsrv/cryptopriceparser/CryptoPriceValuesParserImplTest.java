package com.epam.crpsrv.cryptopriceparser;

import com.epam.crpsrv.cryptoprice.CryptoPriceParserConfig;
import com.epam.crpsrv.cryptoprice.CryptoPriceParserFactoryImpl;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParserUnknown;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParser;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParserImpl;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CryptoPriceParserFactoryImpl.class, CryptoPriceValuesParserImpl.class, CryptoPriceParserUnknown.class})
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
        var actualCryptoPrices = cryptoPriceValuesParser.parse(BTC_VALUES.getBytes(StandardCharsets.UTF_8));
        System.out.println(actualCryptoPrices);
    }
}