package ru.vtb.vzss.neto104.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.vzss.neto104.model.Persons;

import java.util.List;

@Repository
public class PersonsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Persons> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("select p from Persons p where p.cityOfLiving = :city");
        query.setParameter("city", city);
        List<Persons> result = query.getResultList();
        return result;
    }

}
