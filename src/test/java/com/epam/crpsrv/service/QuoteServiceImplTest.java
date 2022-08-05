package com.epam.crpsrv.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.Quote;
import com.epam.crpsrv.quoteparser.QuoteDto;
import com.epam.crpsrv.quoteparser.QuoteValuesParser;
import com.epam.crpsrv.repository.QuoteRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuoteServiceImplTest {

    @Mock
    QuoteRepository quoteRepository;

    @Mock
    QuoteValuesParser quoteValuesParser;

    @Mock
    CryptoService cryptoService;

    @InjectMocks
    QuoteServiceImpl quoteService;

    @Test
    void saveFromByteContent() {
        var inputContent = new byte[]{1, 2, 3};
        var symbol = "BTC";

        var quote1 = QuoteDto.builder()
                .symbol(symbol)
                .price(BigDecimal.TEN)
                .build();

        var quote2 = QuoteDto.builder()
                .symbol(symbol)
                .price(BigDecimal.ONE)
                .build();

        var expectedQuotes = List.of(quote1, quote2);

        var expectedCrypto = Crypto.builder()
                .symbol(symbol)
                .build();

        doReturn(expectedQuotes).when(quoteValuesParser).parse(inputContent);
        doReturn(expectedCrypto).when(cryptoService).saveIfNotExists(symbol);

        quoteService.saveFromByteContent(inputContent);

        var expectedQuote1 = createInstanceFrom(quote1, expectedCrypto);
        var expectedQuote2 = createInstanceFrom(quote2, expectedCrypto);

        verify(quoteRepository, times(1)).save(expectedQuote1);
        verify(quoteRepository, times(1)).save(expectedQuote2);
    }

    private Quote createInstanceFrom(QuoteDto quoteDto, Crypto crypto) {
        return Quote.builder()
                .price(quoteDto.getPrice())
                .crypto(crypto)
                .build();
    }
}