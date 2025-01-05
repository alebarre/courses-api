package com.alebarre.coursesAPI.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


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
	@Length(max = 10)
	@Pattern(regexp = "Back-end|Front-end")
	@Column(length = 10, nullable = false)
	private String category;
	
	@NotNull
	@Length(max = 10)
	@Pattern(regexp = "Ativo|Inativo")
	@Column(length = 10, nullable = false)
	private String status = "Ativo";
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
