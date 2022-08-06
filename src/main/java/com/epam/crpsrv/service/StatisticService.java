package com.epam.crpsrv.service;

import com.epam.crpsrv.model.OldestNewestMinMaxDto;
import java.util.Date;
import java.util.List;

public interface StatisticService {

    List<OldestNewestMinMaxDto> oldestNewestMinMax(Date dateFrom, Date dateTo);

    List<OldestNewestMinMaxDto> oldestNewestMinMax(int month, int year);
}