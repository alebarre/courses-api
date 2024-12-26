package com.alebarre.coursesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alebarre.coursesAPI.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
