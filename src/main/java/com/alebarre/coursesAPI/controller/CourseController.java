package com.alebarre.coursesAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alebarre.coursesAPI.dto.CourseDTO;
import com.alebarre.coursesAPI.dto.CoursePageDTO;
import com.alebarre.coursesAPI.model.Course;
import com.alebarre.coursesAPI.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@Validated
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// @GetMapping
	// public @ResponseBody List<CourseDTO> list(){
	// return courseService.list();
	// }

	@GetMapping
	public @ResponseBody CoursePageDTO list(
			@RequestParam(defaultValue = "0") @PositiveOrZero int page,

			@RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {

		return courseService.list(page, pageSize);
	}

	@GetMapping("/{id}")
	public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return courseService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {
		return courseService.create(course);
	}

	@PutMapping("/{id}")
	public CourseDTO update(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody CourseDTO course) {
		return courseService.update(id, course);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		courseService.delete(id);
	}

}
