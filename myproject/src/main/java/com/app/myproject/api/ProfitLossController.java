package com.app.myproject.api;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.FieldNames;
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
	
	@RequestMapping(value = "/dailyProfitLoss", method = RequestMethod.GET)
	public String getProfitLoss(Model model, @RequestParam(required=false) String type,
			@RequestParam(required=false) String fromDate,
			@RequestParam(required=false) String toDate,
			@RequestParam(required=false) Integer month,
			@RequestParam(required=false) Integer monthlYear,
			@RequestParam(required=false) Integer quarter,
			@RequestParam(required=false) Integer year,
			@PageableDefault(page=1, size=10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchDailyProfitLoss(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging("dailyProfitLoss", page.getPageNumber()+1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.QUARTERS, comboGenerator.getQuarterDropDown(null));
		model.addAttribute(FieldNames.MONTHS, comboGenerator.getMonthDropDown(null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return "dailyProfitLoss";
	}
}
