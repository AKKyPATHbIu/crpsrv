package com.epam.crpsrv.cryptoprice;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParser;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParserPrice;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParserSymbol;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParserTimestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoPriceParserConfig {

    @Bean
    List<CryptoPriceParser> cryptoPriceParsers() {
        var parsers = new ArrayList<CryptoPriceParser>();
        parsers.add(new CryptoPriceParserTimestamp());
        parsers.add(new CryptoPriceParserSymbol());
        parsers.add(new CryptoPriceParserPrice());
        return parsers;
    }
}