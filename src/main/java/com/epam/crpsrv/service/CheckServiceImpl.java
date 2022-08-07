package com.epam.crpsrv.service;

import com.epam.crpsrv.exception.CrpSrvException;
import com.epam.crpsrv.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckServiceImpl implements CheckService {

    @Autowired
    CryptoRepository cryptoRepository;

    @Override
    public void checkIfCryptoExists(String symbol) {
        cryptoRepository.findBySymbolIgnoreCase(symbol)
                .orElseThrow(() -> new CrpSrvException(String.format("Crypto with symbol %s not exists", symbol)));
    }
}