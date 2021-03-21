package org.com.PVZHeroesStatswebapp.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	CriteriaBuilder cb;
	CriteriaQuery<Cartas> cq;
	Root<Cartas> root;
	
	// Se han seguido las directrices de esto:
	// https://www.baeldung.com/spring-data-jpa-query

	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Override
	public List<Cartas> findByValue(int value, String operator, String atributo) {
		defineBeginningOfQuery();

		switch (operator) {
		case ">":
			cq.where(cb.greaterThan(root.get(atributo), value));
			break;
		case "=":
			cq.where(cb.equal(root.get(atributo), value));
			break;
		case "<":
			cq.where(cb.lessThan(root.get(atributo), value));
			break;
		case ">=":
			cq.where(cb.greaterThanOrEqualTo(root.get(atributo), value));
			break;
		case "<=":
			cq.where(cb.lessThanOrEqualTo(root.get(atributo), value));
			break;
		}
		List<Cartas> cartas = entityManager.createQuery(cq).getResultList();
		return cartas;
	}

	@Override
	public List<Cartas> findByPattern(String value, String operator, String atributo) {
		String regExp = "";
		defineBeginningOfQuery();

		switch (operator) {
		case "==":
			cq.where(cb.equal(root.get(atributo), value));
			break;
		case "LIKE":
			regExp = "%".concat(value).concat("%");
			cq.where(cb.like(root.get(atributo), regExp));
			break;
		case "NOT LIKE":
			regExp = "%".concat(value).concat("%");
			cq.where(cb.notLike(root.get(atributo), regExp));
			break;
		}	
		List<Cartas> cartas = entityManager.createQuery(cq).getResultList();
		return cartas;
	}

	private void defineBeginningOfQuery() {
		cb = entityManager.getCriteriaBuilder();
		cq = cb.createQuery(Cartas.class);
		root = cq.from(Cartas.class);
		cq.select(root);
	}


}
