package com.epam.crpsrv.quoteparser;

import com.epam.crpsrv.quoteparser.QuoteDto.QuoteDtoBuilder;
import java.math.BigDecimal;

class QuoteParserPrice implements QuoteParser {

    @Override
    public QuoteDtoBuilder parse(String value, QuoteDtoBuilder builder) {
        return builder.price(new BigDecimal(value));
    }

    @Override
    public String getColumnName() {
        return COLUMN_PRICE;
    }
}