package com.epam.crpsrv.model.namedquery;

import static com.epam.crpsrv.model.namedquery.NamedQueryOldestNewestMinMaxByCrypto.NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO;

import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NamedNativeQuery(
        name = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO,
        query = "select\n"
                + "  q.crypto_id,\n"
                + "  c.symbol,\n"
                + "  coalesce(min(q.price), 0.0) as min_price,\n"
                + "  coalesce(max(q.price), 0.0) as max_price,\n"
                + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" between :dateFrom and :dateTo order by q2.\"timestamp\" limit 1), 0.0) as oldest_price,\n"
                + "  coalesce((select price from crypto_price q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" between :dateFrom and :dateTo order by q2.\"timestamp\" desc limit 1), 0.0) as newest_price\n"
                + "from crypto c\n"
                + "left join crypto_price q on q.crypto_id = c.id and \"timestamp\" between :dateFrom and :dateTo\n"
                + "where c.symbol ilike :symbol\n"
                + "group by crypto_id, c.symbol",
        resultSetMapping = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO
)
@SqlResultSetMapping(
        name = NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO,
        classes = @ConstructorResult(
                targetClass = OldestNewestMinMaxDto.class,
                columns = {
                        @ColumnResult(name = "symbol", type = String.class),
                        @ColumnResult(name = "min_price", type = BigDecimal.class),
                        @ColumnResult(name = "max_price", type = BigDecimal.class),
                        @ColumnResult(name = "oldest_price", type = BigDecimal.class),
                        @ColumnResult(name = "newest_price", type = BigDecimal.class)
                }
        )
)
public class NamedQueryOldestNewestMinMaxByCrypto {

    public static final String NQ_NORMALIZED_OLDEST_NEWEST_MIN_MAX_BY_CRYPTO = "oldest_newest_min_max_by_crypto";

    @Id
    @Column(name = "crypto_id")
    UUID cryptoId;
}