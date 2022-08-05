package com.epam.crpsrv.quoteparser;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuoteParserConfig {

    @Bean
    List<QuoteParser> quoteParsers() {
        var parsers = new ArrayList<QuoteParser>();
        parsers.add(new QuoteParserTimestamp());
        parsers.add(new QuoteParserSymbol());
        parsers.add(new QuoteParserPrice());
        return parsers;
    }
}