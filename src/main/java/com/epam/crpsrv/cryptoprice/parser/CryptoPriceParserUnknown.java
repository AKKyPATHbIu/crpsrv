package com.epam.crpsrv.cryptoprice.parser;

import static com.epam.crpsrv.cryptoprice.parser.CryptoPriceParserUnknown.NAME_QP_UNKNOWN;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto.CryptoPriceDtoBuilder;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(NAME_QP_UNKNOWN)
public class CryptoPriceParserUnknown implements CryptoPriceParser {

    public static final String NAME_QP_UNKNOWN = "cryptoPriceParserUnknown";

    @Override
    public CryptoPriceDtoBuilder parse(String value, CryptoPriceDtoBuilder builder) {
        return builder;
    }

    @Override
    public String getColumnName() {
        return StringUtils.EMPTY;
    }
}