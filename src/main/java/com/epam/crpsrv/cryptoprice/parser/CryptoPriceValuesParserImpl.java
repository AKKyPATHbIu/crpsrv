package com.epam.crpsrv.cryptoprice.parser;

import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import com.epam.crpsrv.cryptoprice.CryptoPriceParserFactory;
import com.epam.crpsrv.exception.CrpSrvException;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CryptoPriceValuesParserImpl implements CryptoPriceValuesParser {

    private static final String DELIMETER = ",";

    @Autowired
    CryptoPriceParserFactory cryptoPriceParserFactory;

    @Autowired
    @Qualifier("cryptoPriceParsers")
    List<CryptoPriceDtoBuilder> cryptoPriceDtoBuilders;

    @Override
    public List<CryptoPriceDto> parse(byte[] content) {
        var br = new BufferedReader(new StringReader(new String(content)));
        var lines = br.lines().collect(Collectors.toList());

        if (lines.isEmpty()) {
            return List.of();
        }

        String header = lines.get(0);
        var columnNames = Arrays.stream(header.split(DELIMETER)).map(h -> h.trim()).collect(Collectors.toList());
        var parsers = columnNames.stream().map(n -> cryptoPriceParserFactory.getInstance(n))
                .collect(Collectors.toList());

        checkHeaders(parsers);

        var result = new ArrayList<CryptoPriceDto>();
        for (int i = 1; i < lines.size(); i++) {
            result.add(parse(parsers, lines.get(i), i));
        }
        return result;
    }

    private void checkHeaders(List<CryptoPriceDtoBuilder> parsers) {
        var missedParsers = cryptoPriceDtoBuilders.stream().filter(p -> !parsers.contains(p))
                .collect(Collectors.toList());
        if (!missedParsers.isEmpty()) {
            var missedHeaders = missedParsers.stream().map(CryptoPriceDtoBuilder::getColumnName).collect(Collectors.toList());
            throw new CrpSrvException(String.format("Headers are missed: %s", String.join(",", missedHeaders)));
        }
    }

    private CryptoPriceDto parse(List<CryptoPriceDtoBuilder> parsers, String value, int lineNumber) {
        var values = Arrays.stream(value.split(DELIMETER)).map(v -> v.trim()).collect(Collectors.toList());
        if (values.size() != parsers.size()) {
            throw new CrpSrvException(String.format("Columns count doesn't match the header, line = %d", lineNumber));
        }
        var builder = CryptoPriceDto.builder();

        for (int i = 0; i < values.size(); i++) {
            parsers.get(i).build(values.get(i), builder);
        }
        return builder.build();
    }
}