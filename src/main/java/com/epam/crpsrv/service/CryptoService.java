package com.epam.crpsrv.service;

import com.epam.crpsrv.model.Crypto;
import java.util.List;
import java.util.UUID;

public interface CryptoService {

    Crypto save(String symbol);

    Crypto saveIfNotExists(String symbol);

    Crypto findById(UUID id);

    List<Crypto> findAll();
}