package com.epam.crpsrv.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.model.Crypto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends EntityGraphJpaRepository<Crypto, UUID> {

    Optional<Crypto> findBySymbol(String symbol);
}