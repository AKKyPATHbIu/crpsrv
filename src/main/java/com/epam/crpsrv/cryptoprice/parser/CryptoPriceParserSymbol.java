package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto.CryptoPriceDtoBuilder;

public class CryptoPriceParserSymbol implements CryptoPriceParser {

    @Override
    public CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder) {
        return builder.symbol(value);
    }

    @Override
    public String getColumnName() {
        return COLUMN_SYMBOL;
    }
}