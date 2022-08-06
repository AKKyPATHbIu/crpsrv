package com.epam.crpsrv.cryptopriceparser;

import java.util.List;

public interface CryptoPriceValuesParser {

    List<CryptoPriceDto> parse(byte[] content);
}