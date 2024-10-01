package ru.akhramova.neto104.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akhramova.neto104.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select p from Person p where p.cityOfLiving = :city")
    List<Person> findByCityOfLiving(String city);

    @Query("select p from Person p where p.age < :age order by p.age")
    List<Person> findByAgeLessThanOrderByAge(int age);

    @Query("select p from Person p where p.name = :name and p.surname = :surname")
    Optional<Person> findByNameAndSurname(String name, String surname);

}
