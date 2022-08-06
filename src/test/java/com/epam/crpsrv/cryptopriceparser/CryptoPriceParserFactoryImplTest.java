package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_PRICE;
import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_SYMBOL;
import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParser.COLUMN_TIMESTAMP;
import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParserUnknown.NAME_QP_UNKNOWN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CryptoPriceParserFactoryImpl.class, CryptoPriceValuesParserImpl.class, CryptoPriceParserUnknown.class})
@Import(value = {CryptoPriceParserConfig.class})
class CryptoPriceParserFactoryImplTest {

    @Autowired
    CryptoPriceParserFactory cryptoPriceParserFactory;

    @Autowired
    @Qualifier(NAME_QP_UNKNOWN)
    CryptoPriceParser cryptoPriceParserUnknown;

    @Nested
    class GetInstance {

        @Test
        void timestamp() {
            check(COLUMN_TIMESTAMP);
        }

        @Test
        void symbol() {
            check(COLUMN_SYMBOL);
        }

        @Test
        void price() {
            check(COLUMN_PRICE);
        }

        @Test
        void unknown() {
            var actualParser = cryptoPriceParserFactory.getInstance("zzz");
            assertThat(actualParser).isEqualTo(cryptoPriceParserUnknown);
        }

        private void check(String columnName) {
            var actualParser = cryptoPriceParserFactory.getInstance(columnName);
            assertThat(actualParser.getColumnName()).isEqualTo(columnName);
        }
    }
}