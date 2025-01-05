package com.alebarre.coursesAPI.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CoursesDTO (
		@JsonProperty("id") Long id, 
		@NotBlank @NotNull @Length(min = 5, max = 100) String name, 
		@NotNull @Length(max = 100) @Pattern(regexp = "Back-end|Front-end") String category) {}