package com.app.myproject.querybuilder;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.SearchCriteria;
import com.app.myproject.util.CommonUtil;

@Component
public class QueryBuilder {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private CommonUtil commonUtil;
	
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

	/*public List<Tuple> getTupleByQuery(QueryDto queryDto) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
		Root<T> root = criteriaQuery.from(getType());

		Map<Integer, Join<?, ?>> joins = doJoin(queryDto.getTableJoins(), root);

		List<Selection<?>> selections = new ArrayList<>();

		for (SelectField field : queryDto.getSelectFields()) {
			if (field.getField() instanceof String) {
				String fieldName = (String) field.getField();
				selections.add(joins.get(field.getJoinIndex()).get(fieldName));
			} else if (field.getField() instanceof AggregateFunction) {
				AggregateFunction function = (AggregateFunction) field
						.getField();
				selections.add(criteriaBuilder.function(function.getName(),
						function.getReturnType()));
			} else if (field.getField() instanceof List) {
				int index = 1;
				List<Expression<?>> functionSelections = new ArrayList<>();
				List<AggregateFunction> functions = (List) field.getField();
				for (AggregateFunction function : functions) {
					if (index == 1) {
						functionSelections.add(criteriaBuilder.function(
								function.getName(), function.getReturnType()));
					} else if (index == functions.size()) {
						selections.add(criteriaBuilder.function(
								function.getName(), function.getReturnType(),
								functionSelections.get(index - 1)));
					} else {
						functionSelections.add(criteriaBuilder.function(
								function.getName(), function.getReturnType(),
								functionSelections.get(index - 1)));
					}
				}
			}
		}

		List<Predicate> predicates = addWhereConditioons(
				queryDto.getWhereConditions(), root, criteriaBuilder, joins);

		criteriaQuery.multiselect(selections);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public List<Predicate> addWhereConditioons(
			List<WhereCondition> whereConditions, Root<T> root,
			CriteriaBuilder builder, Map<Integer, Join<?, ?>> joins) {
		List<Predicate> predicates = new ArrayList<>();
		for (WhereCondition condition : whereConditions) {
			Predicate predicate = null;
			if (IN.equals(condition.getOperation())) {
				predicate = joins.get(condition.getJoinIndex())
						.get(condition.getKey()).in(condition.getValue());
				// root.get(condition.getKey()).in(condition.getValue());
			} else if (NOT_IN.equals(condition.getOperation())) {
				predicate = builder.not(joins.get(condition.getJoinIndex())
						.get(condition.getKey()).in(condition.getValue()));
				// builder.not(root.get(condition.getKey()).in(condition.getValue()));
			} else if (EQUAL.equals(condition.getOperation())) {
				predicate = builder.equal(joins.get(condition.getJoinIndex())
						.get(condition.getKey()), condition.getValue());
				// builder.equal(root.get(condition.getKey()),
				// condition.getValue());
			} else if (IS_NULL.equals(condition.getOperation())) {
				predicate = builder.isNull(joins.get(condition.getJoinIndex())
						.get(condition.getKey()));
				// builder.isNull(root.get(condition.getKey()));
			} else if (NOT_EQUAL.equals(condition.getOperation())) {
				predicate = builder.notEqual(joins
						.get(condition.getJoinIndex()).get(condition.getKey()),
						condition.getValue());
				// builder.notEqual(root.get(condition.getKey()),
				// condition.getValue());
			} else if (GREATER_OR_EQUAL.equals(condition.getOperation())) {
				predicate = builder.greaterThanOrEqualTo(
						joins.get(condition.getJoinIndex()).get(
								condition.getKey()),
						(ZonedDateTime) condition.getValue());
				// builder.greaterThanOrEqualTo(root.get(condition.getKey()),
				// (ZonedDateTime) condition.getValue());
			} else if (LESS_OR_EQUAL.equals(condition.getOperation())) {
				if (condition.getValue() instanceof ZonedDateTime) {
					predicate = builder.lessThanOrEqualTo(
							joins.get(condition.getJoinIndex()).get(
									condition.getKey()),
							(ZonedDateTime) condition.getValue());
					// builder.lessThanOrEqualTo(root.get(condition.getKey()),
					// (ZonedDateTime) condition.getValue());
				} else {
					predicate = builder.lessThanOrEqualTo(
							joins.get(condition.getJoinIndex()).get(
									condition.getKey()),
							(Integer) condition.getValue());
					// builder.lessThanOrEqualTo(root.get(condition.getKey()),
					// (Integer) condition.getValue());
				}
			} else if (LESS.equals(condition.getOperation())) {
				if (condition.getValue() instanceof ZonedDateTime) {
					predicate = builder.lessThan(
							joins.get(condition.getJoinIndex()).get(
									condition.getKey()),
							(ZonedDateTime) condition.getValue());
					// builder.lessThan(root.get(condition.getKey()),
					// (ZonedDateTime) condition.getValue());
				} else {
					predicate = builder.lessThan(
							joins.get(condition.getJoinIndex()).get(
									condition.getKey()),
							(Integer) condition.getValue());
					// builder.lessThan(root.get(condition.getKey()), (Integer)
					// condition.getValue());
				}
			} else if (LIKE.equals(condition.getOperation())) {
				predicate = builder.like(joins.get(condition.getJoinIndex())
						.<String> get(condition.getKey()),
						"%" + condition.getValue() + "%");
				// builder.like(root.<String>get(condition.getKey()), "%" +
				// condition.getValue() + "%");
			}
			predicates.add(predicate);
		}
		return predicates;
	}

	public Map<Integer, Join<?, ?>> doJoin(List<TableJoin> tableJoins,
			Root<T> root) {
		Map<Integer, Join<?, ?>> joins = new HashMap<>();
		int index = 1;
		for (TableJoin join : tableJoins) {
			if (index == 1) {
				joins.put(index,
						root.join(join.getEntityName(), join.getJoinType()));
			} else {
				Join<?, ?> prevJoin = joins.get(index - 1);
				joins.put(index,
						prevJoin.join(join.getEntityName(), join.getJoinType()));
			}
		}

		return joins;
	}*/
}