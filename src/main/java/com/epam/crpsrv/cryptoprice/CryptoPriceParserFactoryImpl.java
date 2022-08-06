package com.epam.crpsrv.cryptoprice;

import static com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderUnknown.NAME_QP_UNKNOWN;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CryptoPriceParserFactoryImpl implements CryptoPriceParserFactory {

    @Autowired
    @Qualifier(NAME_QP_UNKNOWN)
    CryptoPriceDtoBuilder cryptoPriceDtoBuilderUnknown;

    @Autowired
    @Qualifier("cryptoPriceParsers")
    List<CryptoPriceDtoBuilder> cryptoPriceDtoBuilders;

    @Override
    public CryptoPriceDtoBuilder getInstance(String columnName) {
        return cryptoPriceDtoBuilders.stream()
                .filter(qp -> qp.isHandlerFor(columnName))
                .findFirst()
                .orElse(cryptoPriceDtoBuilderUnknown);
    }
}