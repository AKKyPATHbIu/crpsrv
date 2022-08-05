package com.epam.crpsrv.quoteparser;

public interface QuoteParser {

    String COLUMN_TIMESTAMP = "timestamp";
    String COLUMN_SYMBOL = "symbol";
    String COLUMN_PRICE = "price";

    QuoteDto.QuoteDtoBuilder parse(String value, QuoteDto.QuoteDtoBuilder builder);

    String getColumnName();

    default boolean isHandlerFor(String columnName) {
        return columnName != null && getColumnName().equalsIgnoreCase(columnName);
    }
}