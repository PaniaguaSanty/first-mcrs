package com.mcrs.personsInput.person.mapper;


import com.mcrs.personsInput.person.dto.request.NewPersonRequestDto;
import com.mcrs.personsInput.person.dto.response.NewPersonResponseDto;
import com.mcrs.personsInput.person.model.PersonNew;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewPersonMapperService {

    private final ModelMapper modelMapper;

    public NewPersonMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NewPersonResponseDto convertToDto(PersonNew personNew) {
        return modelMapper.map(personNew, NewPersonResponseDto.class);
    }

    public PersonNew convertToEntity(NewPersonRequestDto newPersonRequestDto) {
        return modelMapper.map(newPersonRequestDto, PersonNew.class);
    }

    public List<NewPersonResponseDto> convertToDtoAllNewPersons(List<PersonNew> allPersonNews) {
        return allPersonNews.stream()
                .map(this::convertToDto) // Convierte cada objeto  NewPerson a NewPersonResponseDto
                .collect(Collectors.toList()); // Crea una lista con los objetos convertidos
    }
}

