package com.epam.crpsrv.quoteparser;

import com.epam.crpsrv.quoteparser.QuoteDto.QuoteDtoBuilder;
import java.util.Date;

class QuoteParserTimestamp implements QuoteParser {

    @Override
    public QuoteDtoBuilder parse(String value, QuoteDtoBuilder builder) {
        return builder.timestamp(new Date(Long.parseLong(value)));
    }

    @Override
    public String getColumnName() {
        return COLUMN_TIMESTAMP;
    }
}