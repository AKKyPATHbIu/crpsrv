package com.epam.crpsrv.cryptoprice;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceParser;

public interface CryptoPriceParserFactory {

    CryptoPriceParser getInstance(String columnName);
}