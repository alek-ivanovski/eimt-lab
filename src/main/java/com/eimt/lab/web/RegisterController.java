package com.eimt.lab.web;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.FormEmployee;
import com.eimt.lab.registration.OnRegisterCompletedEvent;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private EmployeeService employeeService;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegisterController(EmployeeService employeeService, ApplicationEventPublisher eventPublisher) {
        this.employeeService = employeeService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public Model showRegistrationForm(Model model) {
        model.addAttribute("employee", new FormEmployee());
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView postRegistrationForm(@ModelAttribute("employee") @Valid FormEmployee formEmployee,
                                             BindingResult bindingResult,
                                             Model model,
                                             HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors() || !formEmployee.getPassword().equals(formEmployee.getConfirmPassword())) {
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            if (!formEmployee.getPassword().equals(formEmployee.getConfirmPassword()))
                model.addAttribute("passNoMatch", "Passwords don't match");
            model.addAttribute("employee", formEmployee);
            return new ModelAndView("register", "employee", formEmployee);

        } else {

            Employee registered = employeeService.saveEmployee(formEmployee);
            if (registered == null)
                bindingResult.reject("email", "messege.regError");
            if (bindingResult.hasErrors()) {
                return new ModelAndView("register", "employee", formEmployee);

            } else {

                try {
                    String appUrl = httpServletRequest.getProtocol() + "://" +
                            httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
                    eventPublisher.publishEvent(new OnRegisterCompletedEvent
                            (registered, httpServletRequest.getLocale(), appUrl));
                } catch (Exception me) {
                    System.out.println("me Exception caught");
                    return new ModelAndView("register", "employee", formEmployee);
                }

                return new ModelAndView("confirmation-email", "employee", registered);
            }
        }

    }

}
