package com.epam.crpsrv.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.CryptoPrice;
import com.epam.crpsrv.cryptoprice.CryptoPriceDto;
import com.epam.crpsrv.cryptoprice.parser.CryptoPriceValuesParser;
import com.epam.crpsrv.repository.CryptoPriceRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoPriceServiceImplTest {

    @Mock
    CryptoPriceRepository cryptoPriceRepository;

    @Mock
    CryptoPriceValuesParser cryptoPriceValuesParser;

    @Mock
    CryptoService cryptoService;

    @InjectMocks
    CryptoPriceServiceImpl cryptoPriceService;

    @Test
    void saveFromByteContent() {
        var inputContent = new byte[]{1, 2, 3};
        var symbol = "BTC";

        var cryptoPrice1 = CryptoPriceDto.builder()
                .symbol(symbol)
                .price(BigDecimal.TEN)
                .build();

        var cryptoPrice2 = CryptoPriceDto.builder()
                .symbol(symbol)
                .price(BigDecimal.ONE)
                .build();

        var expectedCryptoPrices = List.of(cryptoPrice1, cryptoPrice2);

        var expectedCrypto = Crypto.builder()
                .symbol(symbol)
                .build();

        doReturn(expectedCryptoPrices).when(cryptoPriceValuesParser).parse(inputContent);
        doReturn(expectedCrypto).when(cryptoService).saveIfNotExists(symbol);

        cryptoPriceService.saveFromByteContent(inputContent);

        var expectedCryptoPrice1 = createInstanceFrom(cryptoPrice1, expectedCrypto);
        var expectedCryptoPrice2 = createInstanceFrom(cryptoPrice2, expectedCrypto);

        verify(cryptoPriceRepository, times(1)).save(expectedCryptoPrice1);
        verify(cryptoPriceRepository, times(1)).save(expectedCryptoPrice2);
    }

    private CryptoPrice createInstanceFrom(CryptoPriceDto cryptoPriceDto, Crypto crypto) {
        return CryptoPrice.builder()
                .price(cryptoPriceDto.getPrice())
                .crypto(crypto)
                .build();
    }
}