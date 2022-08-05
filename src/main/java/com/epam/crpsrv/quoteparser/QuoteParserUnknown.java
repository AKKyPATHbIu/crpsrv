package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParserUnknown.NAME_QP_UNKNOWN;

import com.epam.crpsrv.quoteparser.QuoteDto.QuoteDtoBuilder;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(NAME_QP_UNKNOWN)
public class QuoteParserUnknown implements QuoteParser {

    public static final String NAME_QP_UNKNOWN = "quoteParserUnknown";

    @Override
    public QuoteDtoBuilder parse(String value, QuoteDtoBuilder builder) {
        return builder;
    }

    @Override
    public String getColumnName() {
        return StringUtils.EMPTY;
    }
}