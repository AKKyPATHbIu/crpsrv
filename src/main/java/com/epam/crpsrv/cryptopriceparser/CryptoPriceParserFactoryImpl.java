package com.epam.crpsrv.cryptopriceparser;

import static com.epam.crpsrv.cryptopriceparser.CryptoPriceParserUnknown.NAME_QP_UNKNOWN;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CryptoPriceParserFactoryImpl implements CryptoPriceParserFactory {

    @Autowired
    @Qualifier(NAME_QP_UNKNOWN)
    CryptoPriceParser cryptoPriceParserUnknown;

    @Autowired
    @Qualifier("cryptoPriceParsers")
    List<CryptoPriceParser> cryptoPriceParsers;

    @Override
    public CryptoPriceParser getInstance(String columnName) {
        return cryptoPriceParsers.stream()
                .filter(qp -> qp.isHandlerFor(columnName))
                .findFirst()
                .orElse(cryptoPriceParserUnknown);
    }
}