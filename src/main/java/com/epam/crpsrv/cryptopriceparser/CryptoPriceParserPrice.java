package com.epam.crpsrv.cryptopriceparser;

import com.epam.crpsrv.cryptopriceparser.CryptoPriceDto.CryptoPriceDtoBuilder;
import java.math.BigDecimal;

class CryptoPriceParserPrice implements CryptoPriceParser {

    @Override
    public CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder) {
        return builder.price(new BigDecimal(value));
    }

    @Override
    public String getColumnName() {
        return COLUMN_PRICE;
    }
}