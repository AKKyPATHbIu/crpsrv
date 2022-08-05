package com.epam.crpsrv.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.Quote;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface QuoteRepository extends EntityGraphJpaRepository<Quote, UUID> {

    Optional<Quote> findByTimestampAndCrypto(Date timestamp, Crypto crypto);
}