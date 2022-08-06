package com.epam.crpsrv.cryptopriceparser;

public interface CryptoPriceParserFactory {

    CryptoPriceParser getInstance(String columnName);
}