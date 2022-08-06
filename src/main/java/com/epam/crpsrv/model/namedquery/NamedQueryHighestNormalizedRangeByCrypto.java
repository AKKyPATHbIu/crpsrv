package com.epam.crpsrv.model.namedquery;

import static com.epam.crpsrv.model.namedquery.NamedQueryCryptoNormalizedRange.NQ_NORMALIZED_RANGE_ALL;
import static com.epam.crpsrv.model.namedquery.NamedQueryHighestNormalizedRangeByCrypto.NQ_NORMALIZED_RANGE_BY_CRYPTO;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import java.math.BigDecimal;
import java.util.UUID;
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
        name = NQ_NORMALIZED_RANGE_BY_CRYPTO,
        query = "select\n"
                + "  c.symbol,\n"
                + "  coalesce((select trunc((max(price) - min(price)) / min(price), 4) from crypto_price q where q.crypto_id = c.id and date(\"timestamp\") = :date), 0.0) as norm_range\n"
                + "from crypto c\n"
                + "where c.symbol ilike :symbol\n"
                + "order by norm_range desc",
        resultSetMapping = NQ_NORMALIZED_RANGE_BY_CRYPTO
)
@SqlResultSetMapping(
        name = NQ_NORMALIZED_RANGE_BY_CRYPTO,
        classes = @ConstructorResult(
                targetClass = NormalizedRangeDto.class,
                columns = {
                        @ColumnResult(name = "symbol", type = String.class),
                        @ColumnResult(name = "norm_range", type = BigDecimal.class)
                }
        )
)
public class NamedQueryHighestNormalizedRangeByCrypto {

    public static final String NQ_NORMALIZED_RANGE_BY_CRYPTO = "normalized_range_by_crypto";

    @Id
    UUID id;
}