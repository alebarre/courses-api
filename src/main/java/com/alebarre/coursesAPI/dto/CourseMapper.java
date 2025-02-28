package com.alebarre.coursesAPI.dto;

import org.springframework.stereotype.Component;

import com.alebarre.coursesAPI.enums.Category;
import com.alebarre.coursesAPI.model.Course;
import com.alebarre.coursesAPI.model.Lesson;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {

		if (course == null) {
			return null;
		}

		List<LessonDTO> lessons = course.getLessons()
				.stream()
				.map(item -> new LessonDTO(item.getId(), item.getName(), item.getYoutubeUrl()))
				.collect(Collectors.toList());

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
	}

	public Course toEntity(CourseDTO courseDTO) {

		if (courseDTO == null) {
			return null;
		}

		Course course = new Course();

		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}

		course.setName(courseDTO.name());
		course.setCategory(converterCategoryValue(courseDTO.category()));

		List<Lesson> lessonsData = courseDTO.lessons().stream().map(lessonDTO -> {
			var item = new Lesson();
			item.setId(lessonDTO.id());
			item.setName(lessonDTO.name());
			item.setYoutubeUrl(lessonDTO.youtubeUrl());
			item.setCourse(course);
			return item;
		}).collect(Collectors.toList());

		course.setLessons(lessonsData);

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
