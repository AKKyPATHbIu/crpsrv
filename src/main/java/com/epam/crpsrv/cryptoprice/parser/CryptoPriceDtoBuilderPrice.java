package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import java.math.BigDecimal;

public class CryptoPriceDtoBuilderPrice implements CryptoPriceDtoBuilder {

    @Override
    public CryptoPriceDto.CryptoPriceDtoBuilder build(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder) {
        return builder.price(new BigDecimal(value));
    }

    @Override
    public String getColumnName() {
        return COLUMN_PRICE;
    }
}