package com.alebarre.coursesAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alebarre.coursesAPI.model.Course;
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
			c.setCategory("front-end");
			
			courseRepository.save(c);
			
		};
	}

}
