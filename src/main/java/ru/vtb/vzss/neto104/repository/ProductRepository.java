package ru.vtb.vzss.neto104.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getProductsByCustomer(String name) {
        Query query = entityManager.createQuery("SELECT o.productName FROM Order o " +
                "JOIN  Customer c  ON c.id = o.customer.id " +
                "WHERE c.name = :name");
        query.setParameter("name", name);
        List<String> products = query.getResultList();
        return products;
    }


}
