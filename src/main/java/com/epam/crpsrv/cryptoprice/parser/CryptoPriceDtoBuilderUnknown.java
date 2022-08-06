package com.epam.crpsrv.cryptoprice.parser;

import static com.epam.crpsrv.cryptoprice.parser.CryptoPriceDtoBuilderUnknown.NAME_QP_UNKNOWN;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(NAME_QP_UNKNOWN)
public class CryptoPriceDtoBuilderUnknown implements CryptoPriceDtoBuilder {

    public static final String NAME_QP_UNKNOWN = "cryptoPriceParserUnknown";

    @Override
    public CryptoPriceDto.CryptoPriceDtoBuilder build(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder) {
        return builder;
    }

    @Override
    public String getColumnName() {
        return StringUtils.EMPTY;
    }
}