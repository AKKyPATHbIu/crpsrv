package com.epam.crpsrv.service;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    List<OldestNewestMinMaxDto> oldestNewestMinMax(LocalDate dateFrom, LocalDate dateTo);

    List<OldestNewestMinMaxDto> oldestNewestMinMax(int month, int year);
}