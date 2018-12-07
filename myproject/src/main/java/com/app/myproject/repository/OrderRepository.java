package com.app.myproject.repository;

import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.myproject.dto.ProfitLossDto;
import com.app.myproject.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query(value = "select date(o.order_date) orderDate, count(o.order_id) noOfOrders, sum(od.quantity) soldQuantity, sum(o.total_amount) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id group by date(o.order_date)", nativeQuery = true)
	Page<ProfitLossDto> getDailyProfitAndLossStatement(Pageable pageable);
	
	@Query(value = "select month(o.order_date) date, sum(o.total_amount) amount_received from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id group by year(o.order_date), month(o.order_date)", nativeQuery = true)
	java.util.Map<Integer, Double> getMonthlySales();
	
	Long countByOrderDate(ZonedDateTime orderDate);
	
	Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate);
}
