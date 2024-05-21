package com.siwar.API_pointeuse.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	@Column(nullable = false)
	private String email;
	@Column(nullable = true)
	private String phoneNumber;
	@Column(nullable = true)
	private String job;
	private Role role;
	@OneToMany()
	private List<Notification> notifications= new ArrayList<>();

	@OneToMany()
	private List<Pointing> pointings= new ArrayList<>();
	@OneToMany()
	private List<Leave> leaves= new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "team_id")
	@JsonIgnoreProperties("users")
	private Team team;

	@OneToMany()
	private List<Assiduity> assiduities= new ArrayList<>();

}
