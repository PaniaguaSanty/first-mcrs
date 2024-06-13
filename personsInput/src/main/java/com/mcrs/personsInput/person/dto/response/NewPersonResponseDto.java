package com.mcrs.personsInput.person.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPersonResponseDto {

    private String id;

    private String name;
    private String dni;
    private String email;
}
