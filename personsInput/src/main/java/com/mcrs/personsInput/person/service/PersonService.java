package com.mcrs.personsInput.person.service;

import com.mcrs.personsInput.person.dto.request.NewPersonRequestDto;
import com.mcrs.personsInput.person.dto.request.OldPersonRequestDto;
import com.mcrs.personsInput.person.dto.response.NewPersonResponseDto;
import com.mcrs.personsInput.person.dto.response.OldPersonResponseDto;
import com.mcrs.personsInput.person.exception.ServiceException;
import com.mcrs.personsInput.person.mapper.NewPersonMapperService;
import com.mcrs.personsInput.person.mapper.OldPersonMapperService;
import com.mcrs.personsInput.person.model.PersonNew;
import com.mcrs.personsInput.person.model.OldPerson;
import com.mcrs.personsInput.person.repository.NewPersonRepository;
import com.mcrs.personsInput.person.repository.OldPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@XSlf4j
@Service
public class PersonService {

    private final OldPersonRepository oldRepository;
    private final NewPersonRepository newRepository;
    private final OldPersonMapperService oldPersonMapper;
    private final NewPersonMapperService newPersonMapper;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    // CRUD for OldPerson
    public OldPersonResponseDto saveOldPerson(OldPersonRequestDto oldPersonRequestDto) {
        try {
            OldPerson oldPerson = oldPersonMapper.convertToEntity(oldPersonRequestDto);
            OldPerson savedOldPerson = oldRepository.save(oldPerson);
            return oldPersonMapper.convertToDto(savedOldPerson);
        } catch (ServiceException e) {
            logger.error("Error saving old person: {}", e.getMessage(), e);
            throw new ServiceException("Error saving OldPerson", e);
        }
    }

    public OldPersonResponseDto updateOldPerson(String dni, OldPersonRequestDto oldPersonRequestDto) {
        OldPerson existingPerson = oldRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with current id"));

        existingPerson.setName(oldPersonRequestDto.getName());
        existingPerson.setEmail(oldPersonRequestDto.getEmail());

        oldPersonMapper.convertToEntity(oldPersonRequestDto);
        OldPerson updatedOldPerson = oldRepository.save(existingPerson);
        return oldPersonMapper.convertToDto(updatedOldPerson);
    }

    public OldPersonResponseDto deleteOldPerson(String dni) {
        Optional<OldPerson> personToDelete = oldRepository.findByDni(dni);
        if (personToDelete.isPresent()) {
            oldRepository.delete(personToDelete.get());
            return oldPersonMapper.convertToDto(personToDelete.get());
        }
        return null;
    }

    public OldPersonResponseDto findOldPersonByDni(String dni) {
        Optional<OldPerson> oldPerson = oldRepository.findByDni(dni);
        return oldPerson.map(oldPersonMapper::convertToDto).orElse(null);
    }

    public List<OldPersonResponseDto> findAllOldPersons() {
        List<OldPerson> allOldPersons = oldRepository.findAll();
        return oldPersonMapper.convertToDtoAllOldPersons(allOldPersons);
    }

    // CRUD for NewPerson

    public NewPersonResponseDto saveNewPerson(NewPersonRequestDto newPersonRequestDto) {
        try {
            PersonNew newPerson = newPersonMapper.convertToEntity(newPersonRequestDto);
            PersonNew savedNewPerson = newRepository.save(newPerson);
            return newPersonMapper.convertToDto(savedNewPerson);
        } catch (ServiceException e) {
            logger.error("Error saving new person: {}", e.getMessage(), e);
            throw new ServiceException("Error saving OldPerson", e);
        }
    }

    public NewPersonResponseDto updateNewPerson(String dni, NewPersonRequestDto newPersonRequestDto) {
        PersonNew existingPerson = newRepository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with current id"));

        existingPerson.setName(newPersonRequestDto.getName());
        existingPerson.setEmail(newPersonRequestDto.getEmail());

        newPersonMapper.convertToEntity(newPersonRequestDto);
        PersonNew updatedNewPerson = newRepository.save(existingPerson);
        return newPersonMapper.convertToDto(updatedNewPerson);
    }

    public NewPersonResponseDto deleteNewPerson(String dni) {
        Optional<PersonNew> newPerson = newRepository.findByDni(dni);
        if (newPerson.isPresent()) {
            newRepository.delete(newPerson.get());
            return newPersonMapper.convertToDto(newPerson.get());
        }
        return null;
    }

    public NewPersonResponseDto findNewPersonByDni(String dni) {
        Optional<PersonNew> newPerson = newRepository.findByDni(dni);
        return newPerson.map(newPersonMapper::convertToDto).orElse(null);
    }

    public List<NewPersonResponseDto> findAllNewPersons() {
        List<PersonNew> allNewPersons = newRepository.findAll();
        return newPersonMapper.convertToDtoAllNewPersons(allNewPersons);
    }



    // Migrar persona de MySQL a MongoDB
    public NewPersonResponseDto migratePerson(String dni) {
        try {
            Optional<OldPerson> oldPerson = oldRepository.findByDni(dni);
            if (oldPerson.isPresent()) {
                PersonNew person = PersonNew.builder()
                        .name(oldPerson.get().getName()) //el primer get es por la verificación del optional.
                        .email(oldPerson.get().getEmail())
                        .dni(oldPerson.get().getDni())
                        .build();
                PersonNew savedNewPerson = newRepository.save(person);
                return newPersonMapper.convertToDto(savedNewPerson);
            }
            return null;
        } catch (ServiceException e) {
            logger.error("Error al migrar persona con DNI {}", dni, e);
            return null;
        }
    }
}


