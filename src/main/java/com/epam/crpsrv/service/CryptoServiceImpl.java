package com.epam.crpsrv.service;

import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.repository.CryptoRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Override
    public Crypto save(String symbol) {
        var crypto = Crypto.builder().symbol(symbol)
                .build();
        return cryptoRepository.save(crypto);
    }

    @Override
    public Crypto saveIfNotExists(String symbol) {
        return cryptoRepository
                .findBySymbolIgnoreCase(symbol)
                .orElseGet(() -> cryptoRepository.save(Crypto.builder()
                        .symbol(symbol)
                        .build())
                );
    }

    @Override
    public Crypto findById(UUID id) {
        return cryptoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Crypto> findAll() {
        return cryptoRepository.findAll();
    }
}