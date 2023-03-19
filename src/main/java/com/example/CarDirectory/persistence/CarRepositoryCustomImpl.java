package com.example.CarDirectory.persistence;

import com.example.CarDirectory.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.HashMap;
import java.util.List;

public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Этот метод строит и исполняет запрос на основе критериев, переданных в виде пар
     * ключей-значений словаря, где ключи - названия полей в таблице, а значения -
     * значения этих полей.
     * @param criteria
     * @return
     */
    @Override
    public List<Car> findCarsByCriteria(HashMap<String, String> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);
        Predicate[] predicates = criteria.keySet().stream()
                .map(key -> cb.equal(root.get(key), criteria.get(key)))
                .toArray(Predicate[]::new);
        return entityManager.createQuery(query.where(predicates)).getResultList();
    }
}
