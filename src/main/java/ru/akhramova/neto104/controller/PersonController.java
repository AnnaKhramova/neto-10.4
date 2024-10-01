package ru.akhramova.neto104.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.akhramova.neto104.model.Person;
import ru.akhramova.neto104.repository.PersonRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("/hello")
    public String hello(String username) {
        return "Hello, " + username;
    }

    @RolesAllowed({"ROLE_READ"})
    @GetMapping("/by-city")
    public ResponseEntity<List<Person>> getPersonByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(personRepository.findByCityOfLiving(city));
    }

    @PostAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/less-than")
    public ResponseEntity<List<Person>> getPersonByAge(@RequestParam("age") int age) {
        return ResponseEntity.ok(personRepository.findByAgeLessThanOrderByAge(age));
    }

    @Secured({"ROLE_READ"})
    @GetMapping("/by-name")
    public ResponseEntity<Optional<Person>> getPersonByNameAndSurname(
                                                    @RequestParam("name") String name,
                                                    @RequestParam("surname") String surname
    ) {
        return ResponseEntity.ok(personRepository.findByNameAndSurname(name, surname));
    }

    @RolesAllowed({"ROLE_WRITE"})
    @PostMapping("/add")
    public void addNewPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

}
