package com.alebarre.coursesAPI.dto;

import org.springframework.stereotype.Component;

import com.alebarre.coursesAPI.enums.Category;
import com.alebarre.coursesAPI.model.Course;

@Component
public class CourseMapper {
	
	public CourseDTO toDTO(Course course) {
		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons());
	}
	
	public Course toEntity(CourseDTO courseDTO) {
		
		if (courseDTO == null) {
			return null;
		}
		
		Course course = new Course();
		
		if(courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}
		
		course.setName(courseDTO.name());
		course.setCategory(converterCategoryValue(courseDTO.category()));
		return course;
	}
	
	public Category converterCategoryValue(String value) {
		if (value == null) {
			return null;
		}
		return switch (value) {
			case "Front-end" -> Category.FRONT_END;
			case "Back-end" -> Category.BACK_END;
			default -> throw new IllegalArgumentException("Categoria inválida: " + value);
		};
	}

}
