package ru.vtb.vzss.neto104.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.vzss.neto104.model.Persons;
import ru.vtb.vzss.neto104.repository.PersonsRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {
    @Autowired
    private PersonsRepository personsRepository;

    @GetMapping("/by-city")
    public ResponseEntity<List<Persons>> getPersonsByCity(@RequestParam("city") String city) {
        return ResponseEntity.ok(personsRepository.getPersonsByCity(city));
    }

}
