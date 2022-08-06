package com.epam.crpsrv.repository;

import static com.epam.crpsrv.model.namedquery.NamedQueryCryptoNormalizedPrice.NQ_NORMALIZED_PRICE_ALL;
import static com.epam.crpsrv.model.namedquery.NamedQueryOldestNewestMinMax.NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.dto.NormalizedPriceDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.model.Quote;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends EntityGraphJpaRepository<Quote, UUID> {

    @Query(name = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX, nativeQuery = true)
    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

    @Query(name = NQ_NORMALIZED_PRICE_ALL, nativeQuery = true)
    List<NormalizedPriceDto> calcNormalizedPrice();
}