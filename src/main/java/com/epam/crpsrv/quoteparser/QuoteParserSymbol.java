package com.epam.crpsrv.quoteparser;

import com.epam.crpsrv.quoteparser.QuoteDto.QuoteDtoBuilder;

class QuoteParserSymbol implements QuoteParser {

    @Override
    public QuoteDtoBuilder parse(String value, QuoteDtoBuilder builder) {
        return builder.symbol(value);
    }

    @Override
    public String getColumnName() {
        return COLUMN_SYMBOL;
    }
}