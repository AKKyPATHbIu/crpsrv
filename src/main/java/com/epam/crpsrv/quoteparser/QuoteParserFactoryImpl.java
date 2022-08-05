package com.epam.crpsrv.quoteparser;

import static com.epam.crpsrv.quoteparser.QuoteParserUnknown.NAME_QP_UNKNOWN;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QuoteParserFactoryImpl implements QuoteParserFactory {

    @Autowired
    @Qualifier(NAME_QP_UNKNOWN)
    QuoteParser quoteParserUnknown;

    @Autowired
    @Qualifier("quoteParsers")
    List<QuoteParser> quoteParsers;

    @Override
    public QuoteParser getInstance(String columnName) {
        return quoteParsers.stream()
                .filter(qp -> qp.isHandlerFor(columnName))
                .findFirst()
                .orElse(quoteParserUnknown);
    }
}