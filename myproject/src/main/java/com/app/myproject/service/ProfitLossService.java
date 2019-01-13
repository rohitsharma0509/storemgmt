package com.app.myproject.service;

import org.springframework.data.domain.Pageable;

import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProfitLossDto;

public interface ProfitLossService {
	CustomPage<ProfitLossDto> searchDailyProfitLoss(Pageable pageable);
	CustomPage<ProfitLossDto> searchMonthlyProfitLoss(Pageable pageable, Integer month, Integer year);
	CustomPage<ProfitLossDto> searchQuarterlyProfitLoss(Pageable pageable, Integer quarter, Integer year);
	CustomPage<ProfitLossDto> searchYearlyProfitLoss(Pageable pageable, Integer year);
}
