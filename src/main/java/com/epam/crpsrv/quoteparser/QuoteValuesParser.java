package com.epam.crpsrv.quoteparser;

import java.util.List;

public interface QuoteValuesParser {

    List<QuoteDto> parse(byte[] content);
}