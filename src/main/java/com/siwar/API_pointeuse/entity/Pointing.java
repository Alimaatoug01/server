package com.siwar.API_pointeuse.entity;

import java.io.File;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Pointing {
	public Pointing() {
		// TODO Auto-generated constructor stub
	}

	public Pointing(Integer id2, LocalDateTime dateHour2, String service2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(nullable = false)
	private LocalDateTime dateHour;

	@Column(nullable = false)
	private String service;


	@ManyToOne
	@JoinColumn(name = "emp_id")
	private User employee;


	public Pointing(User employee, LocalDateTime dateHour, String service) {
		this.employee = employee;
		this.dateHour = dateHour;
		this.service = service;
	}

	public Pointing(Integer id2, LocalDateTime dateHour2, String service2, Object employee2) {
		// TODO Auto-generated constructor stub
	}

	public User getEmployee(User employee) {
		return employee;
	}




}
