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
	public Pointing(Integer id2, LocalDateTime dateHour2, String type2, File image2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(nullable = false)
	private LocalDateTime dateHour;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private File image;
	
	 @ManyToOne
	 @JoinColumn(name = "emp_id")
	 private User employee;
	
	
	

}
