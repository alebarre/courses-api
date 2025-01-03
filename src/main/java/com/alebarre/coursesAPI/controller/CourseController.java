package com.alebarre.coursesAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alebarre.coursesAPI.model.Course;
import com.alebarre.coursesAPI.repository.CourseRepository;

import jakarta.persistence.Entity;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<Course> list(){
		return courseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@PathVariable Long id) {
		return courseRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Course> create(@RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(courseRepository.save(course));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
		return courseRepository.findById(id)
				.map(recordFound -> {
					recordFound.setName(course.getName());
					recordFound.setCategory(course.getCategory());
					Course updateCourse = courseRepository.save(recordFound);
					return ResponseEntity.ok().body(updateCourse);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		return courseRepository.findById(id)
				.map(recordFound ->{ 
					courseRepository.deleteById(id);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
		
}
