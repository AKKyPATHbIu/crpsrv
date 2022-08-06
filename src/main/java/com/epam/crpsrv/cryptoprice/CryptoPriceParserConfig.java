package com.epam.crpsrv.cryptoprice;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilder;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderPrice;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderSymbol;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderTimestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoPriceParserConfig {

    @Bean
    List<CryptoPriceDtoBuilder> cryptoPriceParsers() {
        var parsers = new ArrayList<CryptoPriceDtoBuilder>();
        parsers.add(new CryptoPriceDtoBuilderTimestamp());
        parsers.add(new CryptoPriceDtoBuilderSymbol());
        parsers.add(new CryptoPriceDtoBuilderPrice());
        return parsers;
    }
}