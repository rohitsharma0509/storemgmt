package com.app.myproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProfitLossDto;
import com.app.myproject.querybuilder.QueryBuilder;
import com.app.myproject.repository.OrderRepository;
import com.app.myproject.service.ProfitLossService;
import com.app.myproject.util.CommonUtil;

@Service
public class ProfitLossServiceImpl implements ProfitLossService{
	
	@Inject
	private OrderRepository orderRepository;
	
	@Inject
	private QueryBuilder queryBuilder;
	
	@Inject
	private CommonUtil commonUtil;
	
	public Page<ProfitLossDto> getDailyProfitLoss(Pageable pageable) {
		return orderRepository.getDailyProfitAndLossStatement(pageable);
	}
	
	private String getDailyProfitLossQuery(String type, String fromDate, String toDate, Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.order_date) orderDate, (select count(*) from orders where date(order_date)=date(o.order_date)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		if(StringUtils.isEmpty(type) || Constants.DAILY.equals(type)) {
			if(!StringUtils.isEmpty(fromDate)){
				dailyQuery.append(" and date(o.order_date)>='"+fromDate+"'");
			}
			if(!StringUtils.isEmpty(toDate)){
				dailyQuery.append(" and date(o.order_date)<='"+toDate+"'");
			}
			dailyQuery.append(" group by date(o.order_date) order by date(o.order_date) desc");
			dailyQuery.append(" limit "+offset+", "+limit);
		} else {
			dailyQuery.append(" group by date(o.order_date) order by date(o.order_date) desc");
			dailyQuery.append(" limit 0,10");
		}
		return dailyQuery.toString();
	}
	
	private String getMonthlyProfitLossQuery(String type, Integer month, Integer year, Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.order_date) orderDate, (select count(*) from orders where date(order_date)=date(o.order_date)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.order_date)");
		StringBuilder monthlyQuery = new StringBuilder("select concat(lpad(month(orderDate),2,0),'-',year(orderDate)), sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		monthlyQuery.append(dailyQuery);
		monthlyQuery.append(") as temp where 1=1 ");
		if(Constants.MONTHLY.equals(type)){
			if(month != null){
				monthlyQuery.append(" and month(orderDate)="+month);
			}
			if(null != year){
				monthlyQuery.append(" and year(orderDate)="+year);
			}
			monthlyQuery.append(" group by month(orderDate),year(orderDate) order by orderDate desc");
			monthlyQuery.append(" limit "+offset+", "+limit);
		} else {
			monthlyQuery.append(" group by month(orderDate),year(orderDate) order by orderDate desc");
			monthlyQuery.append(" limit 0, 10");
		}
		return monthlyQuery.toString();
	}
	
	private String getQuarterlyProfitLossQuery(String type, Integer quarter, Integer year, Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.order_date) orderDate, (select count(*) from orders where date(order_date)=date(o.order_date)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.order_date)");
		StringBuilder quarterlyQuery = new StringBuilder("select year(orderDate) year, quarter(orderDate) quarter, sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		quarterlyQuery.append(dailyQuery);
		quarterlyQuery.append(") as temp where 1=1");
		if(Constants.QUARTERLY.equals(type)) {
			if(quarter != null){
				quarterlyQuery.append(" and quarter(orderDate)="+quarter);
			}
			if(null != year){
				quarterlyQuery.append("  and year(orderDate)="+year);
			}
			quarterlyQuery.append(" group by year(orderDate), quarter(orderDate)");
			quarterlyQuery.append(" limit "+offset+", "+limit);
		}else {
			quarterlyQuery.append(" group by year(orderDate), quarter(orderDate)");
			quarterlyQuery.append(" limit 0,10");
		}
		return quarterlyQuery.toString();
	}
	
	private String getYearlyProfitLossQuery(String type, Integer year, Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.order_date) orderDate, (select count(*) from orders where date(order_date)=date(o.order_date)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.order_date)");
		StringBuilder yearlyQuery = new StringBuilder("select year(orderDate), sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		yearlyQuery.append(dailyQuery);
		yearlyQuery.append(") as temp where 1=1");
		if(Constants.YEARLY.equals(type)) {
			if(null != year){
				yearlyQuery.append(" and year(orderDate)="+year);
			}
			yearlyQuery.append("group by year(orderDate) order by orderDate desc");
			yearlyQuery.append(" limit "+offset+", "+limit);
		} else {
			yearlyQuery.append("group by year(orderDate) order by orderDate desc");
			yearlyQuery.append(" limit 0,10");
		}
		return yearlyQuery.toString();
	}
	
	public Map<String, CustomPage<ProfitLossDto>> searchProfitLossStatement(String type, String fromDate, String toDate, Integer month, Integer monthYear, Integer quarter, Integer quarterYear, Integer year, Pageable pageable) {
		String dailyQuery = getDailyProfitLossQuery(type, fromDate, toDate, pageable);
		String monthlyQuery = getMonthlyProfitLossQuery(type, month, monthYear, pageable);
		String quarterlyQuery = getQuarterlyProfitLossQuery(type, quarter, quarterYear, pageable);
		String yearlyQuery = getYearlyProfitLossQuery(type, year, pageable);
		
		String[] queries = {dailyQuery, monthlyQuery, quarterlyQuery, yearlyQuery};
		
		for(String query : queries){
			List<Tuple> tuples = queryBuilder.getTupleByQuery(query, null);
			
			for(Tuple tuple : tuples){
				
			}
		}
		
		return null;
	}
	
	public CustomPage<ProfitLossDto> searchDailyProfitLoss(Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder query = new StringBuilder("select date(o.order_date) orderDate, (select count(*) from orders where date(order_date)=date(o.order_date)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id group by date(o.order_date) order by date(o.order_date) desc");
		StringBuilder countQuery = new StringBuilder("select count(*) count from (select count(*) from orders o group by date(o.order_date)) as temp");
		query.append(" limit "+offset+", "+limit);
		List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), null);
		
		List<ProfitLossDto> profitLossDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			ProfitLossDto profitLossDto = new ProfitLossDto();
			profitLossDto.setAmountReceived(Double.parseDouble(String.valueOf(tuple.get("amountReceived"))));
			profitLossDto.setNoOfOrders(Integer.parseInt(String.valueOf(tuple.get("noOfOrders"))));
			profitLossDto.setOrderDate(commonUtil.formatDate(String.valueOf(tuple.get("orderDate")), Constants.YYYY_MM_DD, Constants.DD_MM_YYYY));
			profitLossDto.setProfitOrLoss(Double.parseDouble(String.valueOf(tuple.get("profitOrLoss"))));
			profitLossDto.setSoldQuantity(Integer.parseInt(String.valueOf(tuple.get("soldQuantity"))));
			profitLossDtos.add(profitLossDto);
		}
		
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		CustomPage<ProfitLossDto> page = new CustomPage<>();
		page.setContent(profitLossDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
}
