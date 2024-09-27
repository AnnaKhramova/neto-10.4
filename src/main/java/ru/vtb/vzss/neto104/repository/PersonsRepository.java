package ru.vtb.vzss.neto104.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vtb.vzss.neto104.model.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

    @Query("select p from Persons p where p.cityOfLiving = :city")
    List<Persons> findByCityOfLiving(String city);

    @Query("select p from Persons p where p.age < :age order by p.age")
    List<Persons> findByAgeLessThanOrderByAge(int age);

    @Query("select p from Persons p where p.name = :name and p.surname = :surname")
    Optional<Persons> findByNameAndSurname(String name, String surname);

}
