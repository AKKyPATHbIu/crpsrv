package com.epam.crpsrv.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import com.epam.crpsrv.model.Quote;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends EntityGraphJpaRepository<Quote, UUID> {

    Optional<Quote> findByTimestampAndCrypto(Date timestamp, Crypto crypto);

    @Query(name = "oldest_newest_min_max", nativeQuery = true)
    List<OldestNewestMinMaxDto> oldestNewestMinMax(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}