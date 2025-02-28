package com.alebarre.coursesAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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
	@Profile("dev")
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {
				Course c = new Course();
				c.setName("Curso de Java " + i);
				c.setCategory(Category.BACK_END);
				c.setStatus(Status.ACTIVE);

				Lesson lesson = new Lesson();
				lesson.setName("Introdução");
				lesson.setYoutubeUrl("ytb1.yf.com");
				lesson.setCourse(c);
				c.getLessons().add(lesson);

				Lesson lesson1 = new Lesson();
				lesson1.setName("Java17");
				lesson1.setYoutubeUrl("ytb2.de.com");
				lesson1.setCourse(c);
				c.getLessons().add(lesson1);

				courseRepository.save(c);
			}
		};
	}
}
