package com.epam.crpsrv.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.epam.crpsrv.model.Crypto;
import com.epam.crpsrv.model.CryptoPrice;
import com.epam.crpsrv.model.view.NormalizedRangeView;
import com.epam.crpsrv.model.view.OldestNewestMinMaxView;
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

    @Query(value = "select\n"
            + "  c.symbol,\n"
            + "  coalesce(min(q.price), 0.0) as min_price,\n"
            + "  coalesce(max(q.price), 0.0) as max_price,\n"
            + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" >= :dateFrom and q2.\"timestamp\" < :dateTo order by q2.\"timestamp\" limit 1), 0.0) as oldest_price,\n"
            + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" >= :dateFrom and q2.\"timestamp\" < :dateTo order by q2.\"timestamp\" desc limit 1), 0.0) as newest_price\n"
            + "from crypto c\n"
            + "left join crypto_price q on q.crypto_id = c.id and q.\"timestamp\" >= :dateFrom and q.\"timestamp\" < :dateTo\n"
            + "group by crypto_id, c.symbol", nativeQuery = true)
    List<OldestNewestMinMaxView> calcOldestNewestMinMax(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

    @Query(value = "select\n"
            + "  c.symbol,\n"
            + "  coalesce(min(q.price), 0.0) as min_price,\n"
            + "  coalesce(max(q.price), 0.0) as max_price,\n"
            + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" >= :dateFrom and q2.\"timestamp\" < :dateTo order by q2.\"timestamp\" limit 1), 0.0) as oldest_price,\n"
            + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" >= :dateFrom and q2.\"timestamp\" < :dateTo order by q2.\"timestamp\" desc limit 1), 0.0) as newest_price\n"
            + "from crypto c\n"
            + "left join crypto_price q on q.crypto_id = c.id and q.\"timestamp\" >= :dateFrom and q.\"timestamp\" < :dateTo\n"
            + "where c.symbol ilike :symbol\n"
            + "group by crypto_id, c.symbol", nativeQuery = true)
    OldestNewestMinMaxView calcOldestNewestMinMax(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("symbol") String symbol
    );

    @Query(value = "select\n"
            + "  c.symbol,\n"
            + "  (select trunc((max(price) - min(price)) / min(price), 4) from crypto_price q where q.crypto_id = c.id) as norm_range\n"
            + "from crypto c\n"
            + "order by norm_range desc", nativeQuery = true)
    List<NormalizedRangeView> calcNormalizedRange();

    @Query(value = "select\n"
            + "  c.symbol,\n"
            + "  coalesce((select trunc((max(price) - min(price)) / min(price), 4) from crypto_price q where q.crypto_id = c.id and date(\"timestamp\") = :date), 0.0) as norm_range\n"
            + "from crypto c\n"
            + "where c.symbol ilike :symbol\n"
            + "order by norm_range desc", nativeQuery = true)
    NormalizedRangeView calcNormalizedRange(@Param("date") LocalDate date, @Param("symbol") String symbol);
}