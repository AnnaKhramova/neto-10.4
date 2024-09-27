package ru.vtb.vzss.neto104.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.vzss.neto104.model.Person;
import ru.vtb.vzss.neto104.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<Person>> getpersonByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(personRepository.findByCityOfLiving(city));
    }

    @GetMapping("/less-than")
    public ResponseEntity<List<Person>> getpersonByAge(@RequestParam("age") int age) {
        return ResponseEntity.ok(personRepository.findByAgeLessThanOrderByAge(age));
    }

    @GetMapping("/by-name")
    public ResponseEntity<Optional<Person>> getpersonByNameAndSurname(
                                                    @RequestParam("name") String name,
                                                    @RequestParam("surname") String surname
    ) {
        return ResponseEntity.ok(personRepository.findByNameAndSurname(name, surname));
    }

}
