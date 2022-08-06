package com.epam.crpsrv.model;

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
        name = "oldest_newest_min_max",
        query = "select\n"
                + "  q.crypto_id,\n"
                + "  min(q.price) as min_price,\n"
                + "  max(q.price) as max_price,\n"
                + "  (select price from quote q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" between :dateFrom and :dateTo order by q2.\"timestamp\" limit 1) as oldest_price,\n"
                + "  (select price from quote q2 where q2.crypto_id = q.crypto_id and q2.\"timestamp\" between :dateFrom and :dateTo order by q2.\"timestamp\" desc limit 1) as newest_price,\n"
                + "  c.symbol \n"
                + "from quote q\n"
                + "left join crypto c on c.id = q.crypto_id \n"
                + "where \"timestamp\" between :dateFrom and :dateTo\n"
                + "group by crypto_id, c.symbol",
        resultSetMapping = "oldest_newest_min_max"
)
@SqlResultSetMapping(
        name = "oldest_newest_min_max",
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
public class NamedQueryOldestNewestMinMax {

    @Id
    @Column(name = "crypto_id")
    UUID cryptoId;

    @Column
    String symbol;

    @Column(name = "min_price")
    BigDecimal minPrice;

    @Column(name = "max_price")
    BigDecimal maxPrice;

    @Column(name = "oldest_price")
    BigDecimal oldestPrice;

    @Column(name = "newest_price")
    BigDecimal newestPrice;
}