package com.epam.crpsrv.model;

import static com.epam.crpsrv.model.NamedQueryCryptoNormalizedPrice.NQ_NORMALIZED_PRICE_ALL;

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
        name = NQ_NORMALIZED_PRICE_ALL,
        query = "select\n"
                + "  c.symbol,\n"
                + "  (select trunc((max(price) - min(price)) / min(price),4) from quote q where q.crypto_id = c.id) as norm_price\n"
                + "from crypto c\n"
                + "order by norm_price desc",
        resultSetMapping = NQ_NORMALIZED_PRICE_ALL
)
@SqlResultSetMapping(
        name = NQ_NORMALIZED_PRICE_ALL,
        classes = @ConstructorResult(
                targetClass = NormalizedPriceDto.class,
                columns = {
                        @ColumnResult(name = "symbol", type = String.class),
                        @ColumnResult(name = "norm_price", type = BigDecimal.class)
                }
        )
)
public class NamedQueryCryptoNormalizedPrice {

    public static final String NQ_NORMALIZED_PRICE_ALL = "normalized_price_all";

    @Id
    UUID id;
}