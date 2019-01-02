package com.app.myproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.dto.SearchCriteria;
import com.app.myproject.dto.StockDto;
import com.app.myproject.dto.jaxb.ProductsType;
import com.app.myproject.mapper.ProductMapper;
import com.app.myproject.model.Product;
import com.app.myproject.querybuilder.QueryBuilder;
import com.app.myproject.repository.ProductRepository;
import com.app.myproject.service.ProductService;
import com.app.myproject.util.CommonUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private ProductMapper productMapper;
	
	@Inject
	private CommonUtil commonUtil; 
	
	@Inject
	private QueryBuilder queryBuilder;
	
	@Override
	public ProductDto getProductByIdForCart(Long id) {
		return productMapper.productToProductDto(productRepository.findById(id).get(), true);
	}
	
	@Override
	public ProductDto getProductById(Long id) {
		return productMapper.productToProductDto(productRepository.findById(id).get());
	}
	
	public List<Product> getProductsByIds(Set<Long> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public Product addProduct(ProductDto productDto) {
		return productRepository.save(productMapper.productDtoToProduct(productDto));
	}

	@Override
	public Page<Product> getProducts(Pageable pageable) {
		PageRequest request = PageRequest.of(pageable.getPageNumber() - 1,
				pageable.getPageSize(), pageable.getSort());
		return productRepository.findAll(request);
	}
	
	@Override
	public CustomPage<Product> searchProducts(Pageable pageable, Map<String, String> params) {
		List<SearchCriteria> criterias = new ArrayList<>();
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		StringBuilder query = new StringBuilder("select * from products where 1=1 ");
		StringBuilder countQuery = new StringBuilder("select count(product_id) count from products where 1=1 ");
		
		if(commonUtil.isValid(params.get("categoryId"))){
			query.append(" and category_id=:categoryId");
			countQuery.append(" and category_id=:categoryId");
			criterias.add(new SearchCriteria("categoryId", params.get("categoryId"), Constants.EQUALS));
		}
		if(!StringUtils.isEmpty(params.get("brandName"))){
			query.append(" and brand_name like :brandName");
			countQuery.append(" and brand_name like :brandName");
			criterias.add(new SearchCriteria("brandName", params.get("brandName"), Constants.LIKE));
		}
		if(!StringUtils.isEmpty(params.get("productName"))){
			query.append(" and product_name like :productName");
			countQuery.append(" and product_name like :productName");
			criterias.add(new SearchCriteria("productName", params.get("productName"), Constants.LIKE));
		}
		
		query.append(" limit "+offset+", "+limit);
		System.out.println("Query: "+query);
		List<Product> products = queryBuilder.getByQuery(query.toString(), criterias, Product.class);
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), criterias);
		System.out.println(totalRecords);
		CustomPage<Product> page = new CustomPage<>();
		page.setContent(products);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
	
	@Override
	public CustomPage<ProductDto> searchProductDtos(Pageable pageable, Map<String, String> params) {
		List<SearchCriteria> criterias = new ArrayList<>();
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		StringBuilder query = new StringBuilder("select p.*, p.quantity-ifnull(sum(od.quantity),0) as availableQty from products p left join order_details od on p.product_id=od.product_id where 1=1 ");
		StringBuilder countQuery = new StringBuilder("select count(p.product_id) count from products p where 1=1 ");
		
		if(commonUtil.isValid(params.get("categoryId"))){
			query.append(" and p.category_id=:categoryId");
			countQuery.append(" and p.category_id=:categoryId");
			criterias.add(new SearchCriteria("categoryId", params.get("categoryId"), Constants.EQUALS));
		}
		if(!StringUtils.isEmpty(params.get("brandName"))){
			query.append(" and p.brand_name like :brandName");
			countQuery.append(" and p.brand_name like :brandName");
			criterias.add(new SearchCriteria("brandName", params.get("brandName"), Constants.LIKE));
		}
		if(!StringUtils.isEmpty(params.get("productName"))){
			query.append(" and p.product_name like :productName");
			countQuery.append(" and p.product_name like :productName");
			criterias.add(new SearchCriteria("productName", params.get("productName"), Constants.LIKE));
		}
		
		query.append(" group by p.product_id limit "+offset+", "+limit);
		List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), criterias);
		List<ProductDto> productDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
		    if(!StringUtils.isEmpty(tuple.get("product_id"))) {
		        ProductDto productDto = new ProductDto();
		        productDto.setId(Long.parseLong(String.valueOf(tuple.get("product_id"))));
		        productDto.setCode(String.valueOf(tuple.get("product_code")));
		        productDto.setName(String.valueOf(tuple.get("product_name")));
		        productDto.setQuantity(Integer.parseInt(String.valueOf(tuple.get("availableQty"))));
		        productDto.setPerProductPrice(Double.parseDouble(String.valueOf(tuple.get("per_product_price"))));
		        productDto.setBrandName(String.valueOf(tuple.get("brand_name")));
		        productDto.setCategoryId(Long.valueOf(String.valueOf(tuple.get("category_id"))));
		        productDto.setAlertQuantity(Integer.parseInt(String.valueOf(tuple.get("alert_quantity"))));
		        productDto.setPurchasePrice(Double.parseDouble(String.valueOf(tuple.get("purchase_price"))));
		        productDtos.add(productDto);
		    }
		}

		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), criterias);
		CustomPage<ProductDto> page = new CustomPage<>();
		page.setContent(productDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalRecords(totalRecords);
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		return productMapper.productsToProductDtos(productRepository.findAll());
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product editProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Long getNumberOfProducts() {
		return productRepository.count();
	}
	

	@Override
	public CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params) {
		return getStockDetails(pageable, params, false);
	}
	
	@Override
	public CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params, Boolean isExcel) {
		StringBuilder query = new StringBuilder("select c.category_name, p.brand_name, p.product_id, p.product_code, p.product_name, p.quantity as totalQty, p.quantity-ifnull(sum(od.quantity),0) as availableQty, ifnull(sum(od.quantity),0) as orderedQty from products p left join product_category c on p.category_id=c.category_id left join order_details od on p.product_id=od.product_id where 1=1 ");
		StringBuilder countQuery = new StringBuilder("select count(p.product_id) from products p left join product_category c on p.category_id=c.category_id where 1=1");
		List<SearchCriteria> criterias = new ArrayList<>();
		if(!CollectionUtils.isEmpty(params) && commonUtil.isValid(params.get("categoryId"))){
			query.append(" and c.category_id=:categoryId");
			countQuery.append(" and c.category_id=:categoryId");
			criterias.add(new SearchCriteria("categoryId", params.get("categoryId"), Constants.EQUALS));
		}
		if(!CollectionUtils.isEmpty(params) && !StringUtils.isEmpty(params.get("brandName"))){
			query.append(" and p.brand_name like :brandName");
			countQuery.append(" and p.brand_name like :brandName");
			criterias.add(new SearchCriteria("brandName", params.get("brandName"), Constants.LIKE));
		}
		if(!CollectionUtils.isEmpty(params) && !StringUtils.isEmpty(params.get("productName"))){
			query.append(" and p.product_name like :productName");
			countQuery.append(" and p.product_name like :productName");
			criterias.add(new SearchCriteria("productName", params.get("productName"), Constants.LIKE));
		}
		
		query.append(" group by p.product_id");
		if(!isExcel){
			int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
			int limit = offset + pageable.getPageSize();
			query.append(" limit "+offset+", "+limit);
		}
		System.out.println("Query: "+query);
		List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), criterias);
		
		List<StockDto> stockDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			StockDto stockDto = new StockDto();
			stockDto.setId(Long.parseLong(String.valueOf(tuple.get("product_id"))));
			stockDto.setCode(String.valueOf(tuple.get("product_code")));
			stockDto.setName(String.valueOf(tuple.get("product_name")));
			stockDto.setCategoryName(String.valueOf(tuple.get("category_name")));
			stockDto.setBrandName(String.valueOf(tuple.get("brand_name")));
			stockDto.setTotalQty(Integer.parseInt(String.valueOf(tuple.get("totalQty"))));
			stockDto.setOrderedQty(Integer.parseInt(String.valueOf(tuple.get("orderedQty"))));
			stockDto.setAvailableQty(Integer.parseInt(String.valueOf(tuple.get("availableQty"))));
			stockDtos.add(stockDto);
		}
		CustomPage<StockDto> page = new CustomPage<>();
		if(!isExcel){
			Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), criterias);
			System.out.println(totalRecords);
			page.setPageNumber(pageable.getPageNumber() - 1);
			page.setSize(pageable.getPageSize());
			page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		}
		page.setContent(stockDtos);
		return page;
	}

	@Override
	public Long getAlterProductQuantity() {
		return productRepository.getAlterProductQuantity();
	}

	@Override
	public Long getOutOfStockProductQuantity() {
		return productRepository.getOutOfStockProductQuantity();
	}
	
	@Override
	public void importProducts(MultipartFile multiPartFile, String fileType){
		List<Product> products = null;
		try {
			if("xml".equalsIgnoreCase(fileType)){
				String xml = new String(multiPartFile.getBytes());
				ProductsType productsType = commonUtil.convertXmlToProductsType(xml);
				System.out.println(commonUtil.convertProductsTypeToXml(productsType));
				products = productMapper.convertProductsTypeToProducts(productsType);
			} else if("csv".equalsIgnoreCase(fileType)){
				products = productMapper.convertCsvFileToProducts(multiPartFile.getInputStream());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		productRepository.saveAll(products);
	}
	
	@Override
	public Integer getAvailableQuantity(Long id) {
		return productRepository.getAvailableQuantity(id);
	}
}