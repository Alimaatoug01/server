package com.siwar.API_pointeuse.entity;


import java.util.Date;

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
public class Notification {
	public Notification(Integer id2, String type2, String message2, Date sendingDate2) {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String message;
	@Column(nullable = false)
	private Date sendingDate;
	
	
	  @ManyToOne
		@JoinColumn(name = "emp_id")
	  private User employee;

}
