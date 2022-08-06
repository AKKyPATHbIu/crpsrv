package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto.CryptoPriceDtoBuilder;
import java.math.BigDecimal;

public class CryptoPriceParserPrice implements CryptoPriceParser {

    @Override
    public CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder) {
        return builder.price(new BigDecimal(value));
    }

    @Override
    public String getColumnName() {
        return COLUMN_PRICE;
    }
}