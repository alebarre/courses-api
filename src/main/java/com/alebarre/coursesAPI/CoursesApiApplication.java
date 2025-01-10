package com.alebarre.coursesAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alebarre.coursesAPI.enums.Category;
import com.alebarre.coursesAPI.enums.Status;
import com.alebarre.coursesAPI.model.Course;
import com.alebarre.coursesAPI.model.Lesson;
import com.alebarre.coursesAPI.repository.CourseRepository;

@SpringBootApplication
public class CoursesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return argas -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeUrl("ytb1.yf.com");
			lesson.setCourse(c);
			c.getLessons().add(lesson);

			Lesson lesson1 = new Lesson();
			lesson1.setName("Angular");
			lesson1.setYoutubeUrl("ytb2.de.com");
			lesson1.setCourse(c);
			c.getLessons().add(lesson1);

			courseRepository.save(c);

		};
	}

}
