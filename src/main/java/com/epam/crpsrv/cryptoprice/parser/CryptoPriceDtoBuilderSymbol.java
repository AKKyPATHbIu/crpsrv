package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;

public class CryptoPriceDtoBuilderSymbol implements CryptoPriceDtoBuilder {

    @Override
    public CryptoPriceDto.CryptoPriceDtoBuilder build(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder) {
        return builder.symbol(value);
    }

    @Override
    public String getColumnName() {
        return COLUMN_SYMBOL;
    }
}