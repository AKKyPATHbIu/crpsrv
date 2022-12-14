package com.epam.crpsrv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.repository.CryptoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {

    @Mock
    private CryptoRepository cryptoRepository;

    @InjectMocks
    private CryptoServiceImpl cryptoService;

    private final static UUID EXPECTED_ID = UUID.fromString("ab5364a4-434a-4987-b820-ecb69aecb9fe");
    private final static String EXPECTED_SYMBOL = "BTC";
    private final static Crypto EXPECTED_CRYPTO = Crypto.builder()
            .id(EXPECTED_ID)
            .symbol(EXPECTED_SYMBOL)
            .build();

    @Test
    void save() {
        var expectedCryptoPrice = Crypto.builder()
                .symbol(EXPECTED_SYMBOL)
                .build();

        doReturn(EXPECTED_CRYPTO).when(cryptoRepository).save(expectedCryptoPrice);

        var actualCryptoPrice = cryptoService.save(EXPECTED_SYMBOL);

        assertThat(actualCryptoPrice).isEqualTo(EXPECTED_CRYPTO);
    }

    @Test
    void findById() {
        doReturn(Optional.of(EXPECTED_CRYPTO)).when(cryptoRepository).findById(EXPECTED_ID);

        var actualCryptoPrice = cryptoService.findById(EXPECTED_ID);

        assertThat(actualCryptoPrice).isEqualTo(EXPECTED_CRYPTO);
    }

    @Test
    void findAll() {
        var expectedCryptoPrices = List.of(EXPECTED_CRYPTO);
        doReturn(expectedCryptoPrices).when(cryptoRepository).findAll();

        var actualCryptoPrices = cryptoService.findAll();
        assertThat(actualCryptoPrices).isEqualTo(expectedCryptoPrices);
    }

    @Nested
    class SaveIfNotExists {

        @Test
        void whenNotExists() {
            var expectedCrypto = Crypto.builder()
                    .id(UUID.fromString("89a35751-0839-4995-8ae1-367c34966c7c"))
                    .symbol(EXPECTED_SYMBOL)
                    .build();

            var savedCrypto = Crypto.builder().symbol(EXPECTED_SYMBOL).build();

            doReturn(Optional.empty()).when(cryptoRepository).findBySymbolIgnoreCase(EXPECTED_SYMBOL);
            doReturn(expectedCrypto).when(cryptoRepository).save(savedCrypto);

            var actualCrypto = cryptoService.saveIfNotExists(EXPECTED_SYMBOL);
            assertThat(actualCrypto).isEqualTo(expectedCrypto);
        }

        @Test
        void whenAlreadyExists() {
            doReturn(Optional.of(EXPECTED_CRYPTO)).when(cryptoRepository).findBySymbolIgnoreCase(EXPECTED_SYMBOL);

            var actualCrypto = cryptoService.saveIfNotExists(EXPECTED_SYMBOL);

            assertThat(actualCrypto).isEqualTo(EXPECTED_CRYPTO);
        }
    }
}