package com.epam.crpsrv.service;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import com.epam.crpsrv.exception.CrpSrvException;
import com.epam.crpsrv.repository.CryptoPriceRepository;
import com.epam.crpsrv.util.ViewDtoMapperUtil;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    CryptoPriceRepository cryptoPriceRepository;

    @Override
    public List<OldestNewestMinMaxDto> calcOldestNewestMinMax(LocalDate dateFrom, LocalDate dateTo) {
        return ViewDtoMapperUtil.oldestNewestMinMaxViewToDtoList(
                cryptoPriceRepository.calcOldestNewestMinMax(dateFrom, dateTo)
        );
    }

    @Override
    public List<OldestNewestMinMaxDto> calcOldestNewestMinMax(int month, int year) {
        var dateFrom = LocalDate.of(year, month, 1);
        var dateTo = dateFrom.plusMonths(1);

        return ViewDtoMapperUtil.oldestNewestMinMaxViewToDtoList(
                cryptoPriceRepository.calcOldestNewestMinMax(dateFrom, dateTo)
        );
    }

    @Override
    public OldestNewestMinMaxDto calcOldestNewestMinMax(int month, int year, String symbol) {
        var dateFrom = LocalDate.of(year, month, 1);
        var dateTo = dateFrom.plusMonths(1);

        var result = cryptoPriceRepository.calcOldestNewestMinMax(dateFrom, dateTo, symbol);
        if (result == null) {
            throw new CrpSrvException(String.format("Crypto with symbol %s not exists", symbol));
        }
        return ViewDtoMapperUtil.oldestNewestMinMaxViewToDto(result);
    }

    @Override
    public List<NormalizedRangeDto> calcNormalizedRange() {
        return ViewDtoMapperUtil.normalizedRangeViewToDtoList(
                cryptoPriceRepository.calcNormalizedRange()
        );
    }

    @Override
    public NormalizedRangeDto calcNormalizedRange(LocalDate date, String symbol) {
        return ViewDtoMapperUtil.normalizedRangeViewToDto(
                cryptoPriceRepository.calcNormalizedRange(date, symbol)
        );
    }
}