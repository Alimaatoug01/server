package com.siwar.API_pointeuse.service.impl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.PointingDto;
import com.siwar.API_pointeuse.entity.Pointing;
import com.siwar.API_pointeuse.entity.User;
import com.siwar.API_pointeuse.exception.ResourceNotFoundException;
import com.siwar.API_pointeuse.mapper.PointingMapper;
import com.siwar.API_pointeuse.repos.PointingRepos;
import com.siwar.API_pointeuse.repos.UserRepos;
import com.siwar.API_pointeuse.service.PointingService;

@Service
public class PointingServiceImpl implements PointingService {

	@Autowired
	private PointingRepos pointingrepos;

	@Autowired
	private UserRepos userRepos;

	@Override
	public PointingDto update(PointingDto pointingDto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getAllCsvData() {
		List<String[]> csvData = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Rapportdupresence.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				csvData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Handle exception appropriately
		}
		return csvData;
	}



	@Override
	public PointingDto add() {
		List<Pointing> pointings = new ArrayList<>();
		String line = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Rapportdupresence.csv"))) {
			// Skip the header line
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length < 4) {
					// Skip malformed lines
					continue;
				}

				Integer userId = Integer.parseInt(data[0].trim());
				Optional<User> userOptional = userRepos.findById(userId);
				User user;

				if (userOptional.isPresent()) {
					user = userOptional.get();
				} else {
					// Create a new User if not found
					user = new User();
					user.setId(Long.valueOf(userId));
					user.setUsername(data[1].trim());  // Assuming the name is in the second column
					user = userRepos.save(user);
				}

				String service = data[2].trim();
				String dateHourStr = data[3].trim();
				LocalDateTime dateHour = LocalDateTime.parse(dateHourStr, formatter);

				// Check if a pointing with the same user, dateHour, and service already exists
				boolean pointingExists = pointingrepos.existsByEmployeeAndDateHourAndService(user, dateHour, service);
				if (!pointingExists) {
					Pointing pointing = new Pointing();
					pointing.setEmployee(user);
					pointing.setDateHour(dateHour);
					pointing.setService(service);

					pointings.add(pointing);
				}
			}

			if (!pointings.isEmpty()) {
				pointingrepos.saveAll(pointings);
			}

		} catch (IOException e) {
			e.printStackTrace();
			// Handle exception appropriately
		}

		// Return the last added pointing as a DTO for now (adjust as needed)
		Pointing lastPointing = pointings.isEmpty() ? null : pointings.get(pointings.size() - 1);
		return lastPointing != null ? PointingMapper.mapToPointingDto(lastPointing) : null;
	}

	@PostConstruct
	public void processCsvData() {
		add();
	}

	@Override
	public List<Pointing> getAllPointings() {
		List<Pointing> pointings = pointingrepos.findAll();
		return pointings;
	}

	@Override
	public List<Pointing> getPointingsByUserId(Integer userId) {
		List<Pointing> pointings = pointingrepos.findByEmployeeId(userId);
		return pointings;
	}
}

