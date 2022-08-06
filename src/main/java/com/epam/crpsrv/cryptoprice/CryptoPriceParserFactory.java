package com.epam.crpsrv.cryptoprice;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilder;

public interface CryptoPriceParserFactory {

    CryptoPriceDtoBuilder getInstance(String columnName);
}