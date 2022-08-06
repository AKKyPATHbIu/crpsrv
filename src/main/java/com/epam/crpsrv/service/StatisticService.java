package com.epam.crpsrv.service;

import com.epam.crpsrv.dto.NormalizedRangeDto;
import com.epam.crpsrv.dto.OldestNewestMinMaxDto;
import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(LocalDate dateFrom, LocalDate dateTo);

    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(int month, int year);

    OldestNewestMinMaxDto calcOldestNewestMinMax(int month, int year, String symbol);

    List<NormalizedRangeDto> calcNormalizedRange();

    NormalizedRangeDto calcNormalizedRange(LocalDate date, String symbol);
}