package com.eimt.lab.registration;

import com.eimt.lab.model.Employee;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SendMailOnRegister {

    private final EmployeeService employeeService;

    private final JavaMailSender mailSender;

    @Autowired
    public SendMailOnRegister(EmployeeService employeeService, JavaMailSender mailSender) {
        this.employeeService = employeeService;
        this.mailSender = mailSender;
    }

    @EventListener
    public void onApplicationEvent(OnRegisterCompletedEvent event) {
        Employee employee = event.getEmployee();
        String randomToken = UUID.randomUUID().toString();

        // Generate token
        employeeService.createVerificationToken(employee, randomToken);

        // Send confirmation email
        String employeeEmail = employee.getEmail();
        String subject = "Activate your account";
        String confirmationURL = event.getAppUrl() + "/registrationConfirm?token=" + randomToken;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("alek.ivanovski96@gmail.com");
        email.setTo(employeeEmail);
        email.setSubject(subject);
        email.setText("Activate your account using the following url" + " \n" +
                "http://localhost:8080" +
                confirmationURL);
        mailSender.send(email);
    }


}
