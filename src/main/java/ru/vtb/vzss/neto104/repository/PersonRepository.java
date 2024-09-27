package ru.vtb.vzss.neto104.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.vzss.neto104.model.Person;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getpersonByCity(String city) {
        Query query = entityManager.createQuery("select p from Person p where p.cityOfLiving = :city");
        query.setParameter("city", city);
        List<Person> result = query.getResultList();
        return result;
    }

}
