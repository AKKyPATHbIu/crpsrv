package com.epam.crpsrv.cryptopriceparser;

public interface CryptoPriceParser {

    String COLUMN_TIMESTAMP = "timestamp";
    String COLUMN_SYMBOL = "symbol";
    String COLUMN_PRICE = "price";

    CryptoPriceDto.CryptoPriceDtoBuilder parse(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder);

    String getColumnName();

    default boolean isHandlerFor(String columnName) {
        return columnName != null && getColumnName().equalsIgnoreCase(columnName);
    }
}