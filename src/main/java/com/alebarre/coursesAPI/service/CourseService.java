package com.alebarre.coursesAPI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.alebarre.coursesAPI.dto.CourseDTO;
import com.alebarre.coursesAPI.dto.CourseMapper;
import com.alebarre.coursesAPI.dto.CoursePageDTO;
import com.alebarre.coursesAPI.exception.RecordNotFoundException;
import com.alebarre.coursesAPI.model.Course;
import com.alebarre.coursesAPI.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {

	private final CourseRepository courseRepository;

	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	// Com paginação
	public CoursePageDTO list(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {
		Page<Course> pageCourses = courseRepository.findAll(PageRequest.of(page, pageSize));
		List<CourseDTO> courses = pageCourses.get().map(courseMapper::toDTO).collect(Collectors.toList());
		return new CoursePageDTO(courses, pageCourses.getTotalElements(), pageCourses.getTotalPages());
	}
	// Sem paginação
	// public @ResponseBody List<CourseDTO> list() {
	// return courseRepository.findAll()
	// .stream()
	// .map(courseMapper::toDTO)
	// .collect(Collectors.toList());
	// }

	public CourseDTO findById(@NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(courseMapper::toDTO)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CourseDTO create(@Valid @NotNull CourseDTO course) {
		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
	}

	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
		return courseRepository.findById(id)
				.map(recordFound -> {
					Course course = courseMapper.toEntity(courseDTO);
					recordFound.setName(courseDTO.name());
					recordFound.setCategory(courseMapper.converterCategoryValue(courseDTO.category()));
					recordFound.getLessons().clear();
					course.getLessons().forEach(recordFound.getLessons()::add);
					return courseMapper.toDTO(courseRepository.save(recordFound));
				}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void delete(@NotNull @Positive Long id) {
		courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
