package com.mcrs.personsInput.person.controller;

import com.mcrs.personsInput.person.dto.request.NewPersonRequestDto;
import com.mcrs.personsInput.person.dto.request.OldPersonRequestDto;
import com.mcrs.personsInput.person.dto.response.NewPersonResponseDto;
import com.mcrs.personsInput.person.dto.response.OldPersonResponseDto;
import com.mcrs.personsInput.person.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/createOld")
    public ResponseEntity<OldPersonResponseDto> createOldPerson(@RequestBody OldPersonRequestDto REQUEST) {
        logger.info("Entering create method");
        OldPersonResponseDto RESPONSE = personService.saveOldPerson(REQUEST);
        logger.info("Exiting create method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.CREATED);
    }

    @PutMapping("/updateOld/{dni}")
    public ResponseEntity<OldPersonResponseDto> updateOldPerson(@PathVariable String id, OldPersonRequestDto REQUEST) {
        logger.info("Entering update method");
        OldPersonResponseDto RESPONSE = personService.updateOldPerson(id, REQUEST);
        logger.info("Exiting update method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOld/{dni}")
    public ResponseEntity<OldPersonResponseDto> deleteOldPerson(@PathVariable String dni) {
        logger.info("Entering delete method");
        personService.deleteOldPerson(dni);
        logger.info("Exiting delete method");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAllOld")
    public ResponseEntity<List<OldPersonResponseDto>> findAllOldPersons() {
        logger.info("Entering findAll method");
        List<OldPersonResponseDto> persons = personService.findAllOldPersons();
        logger.info("Exiting findAll method");
        return new ResponseEntity<>(persons, HttpStatus.FOUND);
    }

    @GetMapping("/findOld/{dni}")
    public ResponseEntity<OldPersonResponseDto> findOneOldPerson(@PathVariable String dni) {
        logger.info("Entering findOneOld method");
        return new ResponseEntity<>(personService.findOldPersonByDni(dni), HttpStatus.OK);
    }

    //new Persons


    @PostMapping("/createNew")
    public ResponseEntity<NewPersonResponseDto> createNewPerson(@RequestBody NewPersonRequestDto REQUEST) {
        logger.info("Entering createNew method");
        NewPersonResponseDto RESPONSE = personService.saveNewPerson(REQUEST);
        logger.info("Exiting createNew method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.CREATED);
    }

    @PutMapping("/updateNew/{dni}")
    public ResponseEntity<NewPersonResponseDto> updateNewPerson(@PathVariable String id, NewPersonRequestDto REQUEST) {
        logger.info("Entering updateNew method");
        NewPersonResponseDto RESPONSE = personService.updateNewPerson(id, REQUEST);
        logger.info("Exiting updateNew method");
        return new ResponseEntity<>(RESPONSE, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNew/{dni}")
    public ResponseEntity<NewPersonResponseDto> deleteNewPerson(@PathVariable String dni) {
        logger.info("Entering deleteNew method");
        personService.deleteNewPerson(dni);
        logger.info("Exiting deleteNew method");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAllNew")
    public ResponseEntity<List<NewPersonResponseDto>> findAllNewPersons() {
        logger.info("Entering findAllNew method");
        List<NewPersonResponseDto> persons = personService.findAllNewPersons();
        logger.info("Exiting findAllNew method");
        return new ResponseEntity<>(persons, HttpStatus.FOUND);
    }

    @GetMapping("/findNew/{dni}")
    public ResponseEntity<OldPersonResponseDto> findOne(@PathVariable String dni) {
        logger.info("Entering findOne method");
        return new ResponseEntity<>(personService.findOldPersonByDni(dni), HttpStatus.OK);
    }

    @PostMapping("/migrate")
    public ResponseEntity<NewPersonResponseDto> migratePerson(@RequestParam String dni) {
        NewPersonResponseDto migratedPerson = personService.migratePerson(dni);
        if (migratedPerson != null) {
            return ResponseEntity.ok(migratedPerson);
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404
        }
    }

}
