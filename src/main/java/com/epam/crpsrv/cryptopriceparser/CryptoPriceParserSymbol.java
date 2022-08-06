package com.epam.crpsrv.cryptopriceparser;

import com.epam.crpsrv.cryptopriceparser.CryptoPriceDto.CryptoPriceDtoBuilder;

class CryptoPriceParserSymbol implements CryptoPriceParser {

    @Override
    public CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder) {
        return builder.symbol(value);
    }

    @Override
    public String getColumnName() {
        return COLUMN_SYMBOL;
    }
}