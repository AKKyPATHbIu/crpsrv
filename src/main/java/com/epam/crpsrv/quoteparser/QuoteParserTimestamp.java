package com.epam.crpsrv.quoteparser;

import com.epam.crpsrv.quoteparser.QuoteDto.QuoteDtoBuilder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

class QuoteParserTimestamp implements QuoteParser {

    @Override
    public QuoteDtoBuilder parse(String value, QuoteDtoBuilder builder) {
        var timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(value)),
                TimeZone.getDefault().toZoneId());
        return builder.timestamp(timestamp);
    }

    @Override
    public String getColumnName() {
        return COLUMN_TIMESTAMP;
    }
}