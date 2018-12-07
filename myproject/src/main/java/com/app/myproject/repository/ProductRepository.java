package com.app.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.myproject.dto.StockDto;
import com.app.myproject.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "select count(product_id) from (select p.product_id, p.alert_quantity, p.quantity-ifnull(sum(od.quantity),0) avail_qty from products p left join order_details od on p.product_id=od.product_id group by od.product_id having avail_qty<=p.alert_quantity) as temp", nativeQuery = true)
	Long getAlterProductQuantity();
	
	@Query(value = "select count(product_id) from (select p.product_id, p.quantity-ifnull(sum(od.quantity),0) avail_qty from products p left join order_details od on p.product_id=od.product_id group by od.product_id having avail_qty<=0) as temp", nativeQuery = true)
	Long getOutOfStockProductQuantity();

	@Query(value = "select p.product_id as id, p.product_code as code, p.product_name as name, p.quantity as totalQty, p.quantity-ifnull(sum(od.quantity),0) as availableQty, ifnull(sum(od.quantity),0) as orderedQty from products p left join order_details od on p.product_id=od.product_id group by od.product_id", nativeQuery = true)
	//@Query("select p.product_id as id, p.product_code as code, p.product_name as name, p.quantity as totalQty, p.quantity-ifnull(sum(od.quantity),0) as availableQty, ifnull(sum(od.quantity),0) as orderedQty from products p left join order_details od on p.product_id=od.product_id group by od.product_id")
	List<StockDto> getStockDetails();
	
	@Query(value = "select p.quantity-ifnull(sum(od.quantity),0) as availableQty from products p left join order_details od on p.product_id=od.product_id where p.product_id=:id", nativeQuery = true)
	Integer getAvailableQuantity(@Param("id") Long id);
}
