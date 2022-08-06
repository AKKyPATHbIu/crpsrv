package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class CryptoPriceDtoBuilderTimestamp implements CryptoPriceDtoBuilder {

    @Override
    public CryptoPriceDto.CryptoPriceDtoBuilder build(String value, CryptoPriceDto.CryptoPriceDtoBuilder builder) {
        var timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(value)),
                TimeZone.getDefault().toZoneId());
        return builder.timestamp(timestamp);
    }

    @Override
    public String getColumnName() {
        return COLUMN_TIMESTAMP;
    }
}