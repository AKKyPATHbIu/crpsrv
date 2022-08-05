package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_PRICE;
import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_SYMBOL;
import static com.epam.crpsrv.quoteparser.QuoteParser.COLUMN_TIMESTAMP;
import static com.epam.crpsrv.quoteparser.QuoteParserUnknown.NAME_QP_UNKNOWN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {QuoteParserFactoryImpl.class, QuoteValuesParserImpl.class, QuoteParserUnknown.class})
@Import(value = {QuoteParserConfig.class})
class QuoteParserFactoryImplTest {

    @Autowired
    QuoteParserFactory quoteParserFactory;

    @Autowired
    @Qualifier(NAME_QP_UNKNOWN)
    QuoteParser quoteParserUnknown;

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
            var actualParser = quoteParserFactory.getInstance("zzz");
            assertThat(actualParser).isEqualTo(quoteParserUnknown);
        }

        private void check(String columnName) {
            var actualParser = quoteParserFactory.getInstance(columnName);
            assertThat(actualParser.getColumnName()).isEqualTo(columnName);
        }
    }
}