package com.epam.crpsrv.service;

import com.epam.crpsrv.model.NormalizedPriceDto;
import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(LocalDate dateFrom, LocalDate dateTo);

    List<OldestNewestMinMaxDto> calcOldestNewestMinMax(int month, int year);

    List<NormalizedPriceDto> calcNormalizedPrice();
}