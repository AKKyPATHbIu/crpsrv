package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import java.util.List;

public interface CryptoPriceValuesParser {

    List<CryptoPriceDto> parse(byte[] content);
}