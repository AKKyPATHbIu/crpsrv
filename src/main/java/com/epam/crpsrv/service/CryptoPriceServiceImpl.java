package com.epam.crpsrv.service;

import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParser;
import com.epam.crpsrv.model.CryptoPrice;
import com.epam.crpsrv.repository.CryptoPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CryptoPriceServiceImpl implements CryptoPriceService {

    @Autowired
    CryptoPriceRepository cryptoPriceRepository;

    @Autowired
    CryptoPriceValuesParser cryptoPriceValuesParser;

    @Autowired
    CryptoService cryptoService;

    @Override
    public void saveFromByteContent(byte[] content) {
        var cryptoPrices = cryptoPriceValuesParser.parse(content);
        cryptoPrices.forEach(q -> {
            var symbol = q.getSymbol();
            var crypto = cryptoService.saveIfNotExists(symbol);

            var timestamp = q.getTimestamp();

            var cryptoPriceOpt = cryptoPriceRepository.findByTimestampAndCrypto(timestamp, crypto);

            CryptoPrice cryptoPrice;

            if (cryptoPriceOpt.isPresent()) {
                cryptoPrice = cryptoPriceOpt.get();
                var newPrice = q.getPrice();
                if (!newPrice.equals(cryptoPrice.getPrice())) {
                    cryptoPrice.setPrice(newPrice);
                }
            } else {
                cryptoPrice = CryptoPrice.builder()
                        .crypto(crypto)
                        .timestamp(q.getTimestamp())
                        .price(q.getPrice())
                        .build();
            }

            cryptoPriceRepository.save(cryptoPrice);
        });
    }
}