package com.alebarre.coursesAPI.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.alebarre.coursesAPI.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO (
		@JsonProperty("id") Long id, 
		@NotBlank @NotNull @Length(min = 5, max = 100) String name, 
		@NotNull @Length(max = 100) String category,
		List<Lesson> lessons) {}