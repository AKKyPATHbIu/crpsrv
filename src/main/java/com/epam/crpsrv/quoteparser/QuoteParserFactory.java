package com.epam.crpsrv.quoteparser;

public interface QuoteParserFactory {

    QuoteParser getInstance(String columnName);
}