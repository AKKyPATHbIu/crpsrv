package com.epam.crpsrv.model.namedquery;

import static com.epam.crpsrv.model.namedquery.NamedQueryCryptoNormalizedRange.NQ_NORMALIZED_RANGE_ALL;

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
        name = NQ_NORMALIZED_RANGE_ALL,
        query = "select\n"
                + "  c.symbol,\n"
                + "  (select trunc((max(price) - min(price)) / min(price), 4) from quote q where q.crypto_id = c.id) as norm_range\n"
                + "from crypto c\n"
                + "order by norm_range desc",
        resultSetMapping = NQ_NORMALIZED_RANGE_ALL
)
@SqlResultSetMapping(
        name = NQ_NORMALIZED_RANGE_ALL,
        classes = @ConstructorResult(
                targetClass = NormalizedRangeDto.class,
                columns = {
                        @ColumnResult(name = "symbol", type = String.class),
                        @ColumnResult(name = "norm_range", type = BigDecimal.class)
                }
        )
)
public class NamedQueryCryptoNormalizedRange {

    public static final String NQ_NORMALIZED_RANGE_ALL = "normalized_range_all";

    @Id
    UUID id;
}