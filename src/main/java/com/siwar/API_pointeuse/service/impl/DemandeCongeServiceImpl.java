package com.siwar.API_pointeuse.service.impl;



import com.siwar.API_pointeuse.entity.DemandeConge;
import com.siwar.API_pointeuse.entity.User;
import com.siwar.API_pointeuse.repos.DemandeCongeRepos;
import com.siwar.API_pointeuse.repos.UserRepos;
import com.siwar.API_pointeuse.security.services.MailService;
import com.siwar.API_pointeuse.service.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeCongeServiceImpl implements DemandeCongeService {

    @Autowired
    private DemandeCongeRepos demandeCongeRepository;
    @Autowired
    private UserRepos userRepos;

    @Autowired
    private MailService mailService;

    @Override
    public List<DemandeConge> getAllDemandeConges() {
        return demandeCongeRepository.findAll();
    }

    @Override
    public Optional<DemandeConge> getDemandeCongeById(Long id) {
        return demandeCongeRepository.findById(id);
    }

    @Override
    public DemandeConge saveDemandeConge(DemandeConge demandeConge) {
        return demandeCongeRepository.save(demandeConge);
    }

    @Override
    public void deleteDemandeConge(Long id) {
        demandeCongeRepository.deleteById(id);
    }
    @Override
    public DemandeConge updateDemandeCongeStatus(Long id, boolean confirmed) {
        Optional<DemandeConge> optionalDemandeConge = demandeCongeRepository.findById(id);
        if (optionalDemandeConge.isPresent()) {
            DemandeConge demandeConge = optionalDemandeConge.get();
            demandeConge.setConfirmed(confirmed);

            // Get user from UserRepository
            User user = userRepos.findById(Math.toIntExact(demandeConge.getUser().getId()))
                    .orElseThrow(() -> new RuntimeException("User not found for demandeConge id: " + id));

            DemandeConge updatedDemandeConge = demandeCongeRepository.save(demandeConge);

            // Send email notification
            String subject = "Demande Conge Status Updated";
            String statusColor = confirmed ? "green" : "red";
            String body = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {font-family: Arial, sans-serif;}" +
                    ".container {max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px;}" +
                    ".header {text-align: center; padding-bottom: 20px;}" +
                    ".content {padding: 20px; background-color: #f9f9f9; border-radius: 10px;}" +
                    ".footer {text-align: center; padding-top: 20px;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div class='header'>" +
                    "<h2>" + subject + "</h2>" +
                    "</div>" +
                    "<div class='content'>" +
                    "<p>Dear " + user.getUsername() + ",</p>" +
                    "<p>Your demande conge status has been updated. Here are the details:</p>" +
                    "<p><strong>Status:</strong> <span style='color:" + statusColor + ";'>" + (confirmed ? "Confirmed" : "Refused") + "</span><br>" +
                    "<strong>Start Date:</strong> " + demandeConge.getStartDate() + "<br>" +
                    "<strong>End Date:</strong> " + demandeConge.getEndDate() + "</p>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>Regards,<br>" +
                    "Your HR Team</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            try {
                String email = user.getEmail();
                mailService.sendEmail(email, subject, body);
                System.out.println("Email sent to " + email);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }

            return updatedDemandeConge;
        } else {
            throw new RuntimeException("DemandeConge not found with id: " + id);
        }
    }


}
