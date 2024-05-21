package com.siwar.API_pointeuse.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	private Integer id;
	private String name;
	private Date dateBirth;
	private String email;
	private String phoneNumber;
	private String job;
	private String username;
	private String password;
	


}
