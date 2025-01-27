package com.alebarre.coursesAPI.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.alebarre.coursesAPI.enums.Category;
import com.alebarre.coursesAPI.enums.validations.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
		@JsonProperty("id") Long id,
		@NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotNull @Length(max = 100) @ValueOfEnum(enumClass = Category.class, message = "🛑 Deve ser uma categoria aceita pela aplicação") String category,
		@NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}