package com.epam.crpsrv.service;

import com.epam.crpsrv.model.Quote;
import com.epam.crpsrv.quoteparser.QuoteValuesParser;
import com.epam.crpsrv.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    QuoteValuesParser quoteValuesParser;

    @Autowired
    CryptoService cryptoService;

    @Override
    public void saveFromByteContent(byte[] content) {
        var quotes = quoteValuesParser.parse(content);
        quotes.forEach(q -> {
            var symbol = q.getSymbol();
            var crypto = cryptoService.saveIfNotExists(symbol);

            var quote = Quote.builder()
                    .crypto(crypto)
                    .timestamp(q.getTimestamp())
                    .price(q.getPrice())
                    .build();

            quoteRepository.save(quote);
        });
    }
}