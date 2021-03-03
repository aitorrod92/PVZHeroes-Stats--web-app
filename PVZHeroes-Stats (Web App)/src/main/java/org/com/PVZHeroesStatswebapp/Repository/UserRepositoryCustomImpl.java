package org.com.PVZHeroesStatswebapp.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	// Se han seguido las directrices de esto:
	// https://www.baeldung.com/spring-data-jpa-query

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cartas> findByValue(int value, String operator, String atributo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Cartas> cq = cb.createQuery(Cartas.class);
		Root<Cartas> root = cq.from(Cartas.class);
		cq.select(root);

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

}
