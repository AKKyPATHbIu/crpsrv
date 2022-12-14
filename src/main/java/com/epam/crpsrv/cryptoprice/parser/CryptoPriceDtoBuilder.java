package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;

public interface CryptoPriceDtoBuilder {

    String COLUMN_TIMESTAMP = "timestamp";
    String COLUMN_SYMBOL = "symbol";
    String COLUMN_PRICE = "price";

    CryptoPriceDto.CryptoPriceDtoBuilder build(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder);

    String getColumnName();

    default boolean isHandlerFor(String columnName) {
        return columnName != null && getColumnName().equalsIgnoreCase(columnName);
    }
}