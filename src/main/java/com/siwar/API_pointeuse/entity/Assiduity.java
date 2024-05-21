package com.siwar.API_pointeuse.entity;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Assiduity {
	
	public Assiduity(Integer id, Date date, Boolean presence, Integer nbHourWorked, Integer nbHourDue,
			Boolean arriveLate) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private Boolean presence;
	private Integer nbHourWorked;
	private Integer nbHourDue;
	private Boolean arriveLate;
	
	 @ManyToOne
		@JoinColumn(name = "employee_id")
		private  User employee;

	

}
