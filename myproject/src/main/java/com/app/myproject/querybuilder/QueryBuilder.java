package com.app.myproject.querybuilder;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.SearchCriteria;

@Component
public class QueryBuilder {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
    public <T> List<T> getByQuery(String queryStr, List<SearchCriteria> criterias, Class<T> type) {
		Query query = entityManager.createNativeQuery(queryStr, type);
		
		if(!CollectionUtils.isEmpty(criterias)){
			for (SearchCriteria criteria : criterias){
				if(Constants.EQUALS.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				} else if(Constants.LIKE.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(),"%"+criteria.getValue()+"%");
				} else if(Constants.IN.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				}
			}
		}
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<Tuple> getTupleByQuery(String queryStr, List<SearchCriteria> criterias) {
		Query query = entityManager.createNativeQuery(queryStr, Tuple.class);
		
		if(!CollectionUtils.isEmpty(criterias)){
			for (SearchCriteria criteria : criterias){
				if(Constants.EQUALS.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				} else if(Constants.LIKE.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(),"%"+criteria.getValue()+"%");
				} else if(Constants.IN.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				}
			}
		}
		
		return query.getResultList();
	}
	
	public Integer countByQuery(String queryStr, List<SearchCriteria> criterias) {
		Query query = entityManager.createNativeQuery(queryStr);
		
		if(!CollectionUtils.isEmpty(criterias)){
			for (SearchCriteria criteria : criterias){
				if(Constants.EQUALS.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				} else if(Constants.LIKE.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(),"%"+criteria.getValue()+"%");
				} else if(Constants.IN.equals(criteria.getOperation())){
					query.setParameter(criteria.getKey(), criteria.getValue());
				}
			}
		}
		
		return ((BigInteger) query.getSingleResult()).intValue();
	}
}