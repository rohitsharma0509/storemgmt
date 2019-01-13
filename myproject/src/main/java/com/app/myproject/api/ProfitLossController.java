package com.app.myproject.api;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProfitLossDto;
import com.app.myproject.service.ProfitLossService;
import com.app.myproject.util.ComboGenerator;
import com.app.myproject.util.CommonUtil;

@Controller
public class ProfitLossController {
	@Inject
	private ProfitLossService profitLossService;

	@Inject
	private CommonUtil commonUtil;

	@Inject
	private ComboGenerator comboGenerator;

	@GetMapping(value = RequestUrls.PROFIT_LOSS)
	public String redirectToProfitLoss(Model model) {
		return RequestUrls.PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.DAILY_PROFIT_LOSS)
	public String getProfitLoss(Model model, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchDailyProfitLoss(pageable);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("dailyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.DAILY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.MONTHLY_PROFIT_LOSS)
	public String getMonthlyProfitLoss(Model model, @RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchMonthlyProfitLoss(pageable, month, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("monthlyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.MONTHS, comboGenerator.getMonthDropDown(null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.MONTHLY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.QUARTERLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer quarter,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchQuarterlyProfitLoss(pageable, quarter, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("quarterProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.QUARTERS, comboGenerator.getQuarterDropDown(null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.QUARTERLY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.YEARLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer year,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchYearlyProfitLoss(pageable, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("yearlyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.YEARLY_PROFIT_LOSS;
	}
}
