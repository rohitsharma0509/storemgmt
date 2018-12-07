package com.app.myproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProfitLossDto;

public interface ProfitLossService {
	Page<ProfitLossDto> getDailyProfitLoss(Pageable pageable);
	CustomPage<ProfitLossDto> searchDailyProfitLoss(Pageable pageable);
}
