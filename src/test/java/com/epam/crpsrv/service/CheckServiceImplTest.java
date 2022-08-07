package com.epam.crpsrv.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.exception.CrpSrvException;
import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.repository.CryptoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CheckServiceImplTest {

    @Mock
    CryptoRepository cryptoRepository;

    @InjectMocks
    CheckServiceImpl checkService;

    @Nested
    class CheckIfCryptoExists {

        private final String SYMBOL = "BTC";

        @Test
        void shouldNotThrowException() {
            var crypto = Crypto.builder()
                    .symbol(SYMBOL)
                    .build();

            doReturn(Optional.of(crypto)).when(cryptoRepository).findBySymbolIgnoreCase(SYMBOL);

            checkService.checkIfCryptoExists(SYMBOL);
        }

        @Test
        void shouldThrowException() {
            doReturn(Optional.empty()).when(cryptoRepository).findBySymbolIgnoreCase(SYMBOL);
            assertThatThrownBy(() -> checkService.checkIfCryptoExists(SYMBOL))
                    .isInstanceOf(CrpSrvException.class)
                    .hasMessageContaining("Crypto with symbol BTC not exists");
        }
    }
}