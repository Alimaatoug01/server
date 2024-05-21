package com.siwar.API_pointeuse.Dto;



import com.siwar.API_pointeuse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

	private Integer id;
	private String name;
	private List<User> users;
	
	


}
