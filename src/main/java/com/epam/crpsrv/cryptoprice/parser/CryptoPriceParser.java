package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto.CryptoPriceDtoBuilder;

public interface CryptoPriceParser {

    String COLUMN_TIMESTAMP = "timestamp";
    String COLUMN_SYMBOL = "symbol";
    String COLUMN_PRICE = "price";

    CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder);

    String getColumnName();

    default boolean isHandlerFor(String columnName) {
        return columnName != null && getColumnName().equalsIgnoreCase(columnName);
    }
}