package com.mcrs.personsInput.person.mapper;


import com.mcrs.personsInput.person.dto.request.OldPersonRequestDto;
import com.mcrs.personsInput.person.dto.response.OldPersonResponseDto;
import com.mcrs.personsInput.person.model.OldPerson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OldPersonMapperService {

    private final ModelMapper modelMapper;

    public OldPersonMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OldPersonResponseDto convertToDto(OldPerson oldPerson) {
        return modelMapper.map(oldPerson, OldPersonResponseDto.class);
    }

    public OldPerson convertToEntity(OldPersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, OldPerson.class);
    }

    public List<OldPersonResponseDto> convertToDtoAllOldPersons(List<OldPerson> allOldPersons) {
        return allOldPersons.stream()
                .map(this::convertToDto) // Convierte cada objeto Person a PersonResponseDto
                .collect(Collectors.toList()); // Crea una lista con los objetos convertidos
    }
}

