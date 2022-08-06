package com.epam.crpsrv.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@SuperBuilder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crypto_price")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CryptoPrice {

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @ToString.Include
    @EqualsAndHashCode.Include
    private LocalDateTime timestamp;

    @ToString.Include
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column
    @ToString.Include
    @EqualsAndHashCode.Include
    private BigDecimal price;
}