package com.alebarre.coursesAPI.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.alebarre.coursesAPI.enums.Category;
import com.alebarre.coursesAPI.enums.Status;
import com.alebarre.coursesAPI.enums.converters.CategoryConverter;
import com.alebarre.coursesAPI.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@SuppressWarnings("deprecation")
@Entity
//@Table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "Status = 'Ativo'")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;

	@NotNull
	@NotBlank
	@Length(min = 5, max = 100)
	@Column(length = 100, nullable = false)
	private String name;
	
	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = CategoryConverter.class)
	private Category category;
	
	@NotNull
	@Column(length = 10, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;
	
	@NotNull
	@NotEmpty
	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
	//@JoinColumn(name = "course_id")
	private List<Lesson> lessons = new ArrayList<>();
	

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", category=" + category + ", status=" + status + ", lessons="
				+ lessons + "]";
	}

}