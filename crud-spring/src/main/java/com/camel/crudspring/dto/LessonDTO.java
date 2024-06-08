package com.camel.crudspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(
    Long id,
    @NotBlank @NotNull @Length(min =5, max =100)String name,
    @NotNull @Length(min = 10, max = 11)String urlYoutube
) {
}
