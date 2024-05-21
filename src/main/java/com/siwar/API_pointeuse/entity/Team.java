package com.siwar.API_pointeuse.entity;


import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "team")
	private List<User> users = new ArrayList<>();


}
