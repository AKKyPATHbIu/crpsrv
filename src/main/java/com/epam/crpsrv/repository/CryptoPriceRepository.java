package com.epam.crpsrv.repository;

import static com.epam.crpsrv.model.namedquery.NamedQueryCryptoNormalizedRange.NQ_NORMALIZED_RANGE_ALL;
import static com.epam.crpsrv.model.namedquery.NamedQueryHighestNormalizedRangeByCrypto.NQ_NORMALIZED_RANGE_BY_CRYPTO;
import static com.epam.crpsrv.model.namedquery.NamedQueryOldestNewestMinMax.NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX;
import static com.epam.crpsrv.model.namedquery.NamedQueryOldestNewestMinMaxByCrypto.NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.CryptoPrice;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPriceRepository extends EntityGraphJpaRepository<CryptoPrice, UUID> {

    Optional<CryptoPrice> findByTimestampAndCrypto(LocalDateTime timestamp, Crypto crypto);

    @Query(name = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX, nativeQuery = true)
    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

    @Query(name = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO, nativeQuery = true)
    OldestNewestMinMaxDto calcOldestNewestMinMax(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("symbol") String symbol
    );

    @Query(name = NQ_NORMALIZED_RANGE_ALL, nativeQuery = true)
    List<NormalizedRangeDto> calcNormalizedRange();

    @Query(name = NQ_NORMALIZED_RANGE_BY_CRYPTO, nativeQuery = true)
    NormalizedRangeDto calcNormalizedRange(@Param("date") LocalDate date, @Param("symbol") String symbol);
}