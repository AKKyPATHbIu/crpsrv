package com.epam.crpsrv.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.model.view.NormalizedRangeView;
import com.epam.crpsrv.model.view.OldestNewestMinMaxView;
import com.epam.crpsrv.repository.CryptoPriceRepository;
import com.epam.crpsrv.util.ViewToDtoMapperUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    CryptoPriceRepository cryptoPriceRepository;

    @InjectMocks
    StatisticServiceImpl statisticService;

    @Test
    void calcOldestNewestMinMaxByPeriod() {
        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 1, 10);

        var symbol = "XRP";
        var minPrice = new BigDecimal("0.8298");
        var maxPrice = new BigDecimal("0.8337");

        var expectedDto = OldestNewestMinMaxDto.builder()
                .symbol(symbol)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        var expectedResult = List.of(expectedDto);

        var expectedViews = List.of(
                createOldestNewestMinMaxViewInstance(symbol, minPrice, maxPrice)
        );

        doReturn(expectedViews).when(cryptoPriceRepository).calcOldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.calcOldestNewestMinMax(dateFrom, dateTo);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcOldestNewestMinMaxByMonthAndYear() {
        var year = 2022;
        var month = 1;

        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 2, 1);

        var symbol = "BTC";
        var minPrice = new BigDecimal("46813.21");
        var maxPrice = new BigDecimal("47336.98");

        var expectedDto = OldestNewestMinMaxDto.builder()
                .symbol(symbol)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        var expectedResult = List.of(expectedDto);

        var expectedViews = List.of(
                createOldestNewestMinMaxViewInstance(symbol, minPrice, maxPrice)
        );

        doReturn(expectedViews).when(cryptoPriceRepository).calcOldestNewestMinMax(dateFrom, dateTo);

        var actualResult = statisticService.calcOldestNewestMinMax(month, year);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcOldestNewestMinMaxByMonthAndYearAndSymbol() {
        var year = 2022;
        var month = 1;

        var dateFrom = LocalDate.of(2022, 1, 1);
        var dateTo = LocalDate.of(2022, 2, 1);

        var symbol = "BTC";
        var minPrice = new BigDecimal("35813.21");
        var maxPrice = new BigDecimal("336.98");

        var expectedResult = OldestNewestMinMaxDto.builder()
                .symbol(symbol)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        var expectedView = createOldestNewestMinMaxViewInstance(symbol, minPrice, maxPrice);
        doReturn(expectedView).when(cryptoPriceRepository).calcOldestNewestMinMax(dateFrom, dateTo, symbol);

        var actualResult = statisticService.calcOldestNewestMinMax(month, year, symbol);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private OldestNewestMinMaxView createOldestNewestMinMaxViewInstance(
            String symbol, BigDecimal minPrice, BigDecimal maxPrice) {

        return new OldestNewestMinMaxView() {

            @Override
            public String getSymbol() {
                return symbol;
            }

            @Override
            public BigDecimal getMinPrice() {
                return minPrice;
            }

            @Override
            public BigDecimal getMaxPrice() {
                return maxPrice;
            }

            @Override
            public BigDecimal getOldestPrice() {
                return null;
            }

            @Override
            public BigDecimal getNewestPrice() {
                return null;
            }
        };
    }

    @Test
    void calcNormalizedPrice() {
        var symbolBTC = "BTC";
        var normRangeBTC = new BigDecimal("0.55");

        var symbolXRP = "XRP";
        var normRangeXRP = new BigDecimal("0.21");

        var viewBTC = createNormRangeViewInstance(symbolBTC, normRangeBTC);
        var viewXRP = createNormRangeViewInstance(symbolXRP, normRangeXRP);

        var expectedViews = List.of(viewXRP, viewBTC);
        var expectedResult = ViewToDtoMapperUtil.normalizedRangeViewToDtoList(expectedViews);

        doReturn(expectedViews).when(cryptoPriceRepository).calcNormalizedRange();

        var actualResult = statisticService.calcNormalizedRange();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calcNormalizedPriceByCrypto() {
        var symbol = "XRP";
        var normRange = new BigDecimal("0.21");

        var expectedResult = new NormalizedRangeDto(symbol, normRange);
        var expectedView = createNormRangeViewInstance(symbol, normRange);

        var date = LocalDate.of(2022, 1, 1);

        doReturn(expectedView).when(cryptoPriceRepository).calcNormalizedRange(date, symbol);

        var actualResult = statisticService.calcNormalizedRange(date, symbol);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private NormalizedRangeView createNormRangeViewInstance(String symbol, BigDecimal normRange) {

        return new NormalizedRangeView() {

            @Override
            public String getSymbol() {
                return symbol;
            }

            @Override
            public BigDecimal getNormalizedRange() {
                return normRange;
            }
        };
    }
}