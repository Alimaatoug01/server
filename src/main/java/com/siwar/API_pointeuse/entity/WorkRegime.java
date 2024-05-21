package com.siwar.API_pointeuse.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkRegime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(nullable = false)
	private String type;// seance unique ou full time
	@Column(nullable = false)
	private Time startTime;
	@Column(nullable = false)
	private Time endTime;
	@Column(nullable = false)
	private String day;
	@Column(nullable = false)
	private Time startBreak;
	@Column(nullable = false)
	private Time endBreak;
	@Column(nullable = false)
	private Integer nbHour; 


}
