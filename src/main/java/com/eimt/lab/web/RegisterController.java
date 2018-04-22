package com.eimt.lab.web;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.FormEmployee;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private EmployeeService employeeService;

    @Autowired
    public RegisterController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public Model showRegistrationForm(Model model) {
        model.addAttribute("employee", new FormEmployee());
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView postRegistrationForm(@ModelAttribute("employee") @Valid FormEmployee formEmployee,
                                             BindingResult bindingResult,
                                             Model model) {
        if (bindingResult.hasErrors() || !formEmployee.getPassword().equals(formEmployee.getConfirmPassword())) {
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            if(!formEmployee.getPassword().equals(formEmployee.getConfirmPassword()))
                model.addAttribute("passNoMatch", "Passwords don't match");
            model.addAttribute("employee", formEmployee);
            return new ModelAndView("register", "employee", formEmployee);
        } else {
            Employee registered = employeeService.saveEmployee(formEmployee);
            if(registered == null)
                bindingResult.reject("email", "messege.regError");
            if (bindingResult.hasErrors()) {
                return new ModelAndView("register", "employee", formEmployee);
           } else {
                return new ModelAndView("confirmation-email", "employee", registered);
            }
        }

//        if (bindingResult.hasErrors()) {
//            model.addAttribute("validationErrors", bindingResult.getAllErrors());
//            if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
//                model.addAttribute("passNoMatch", "Passwords don't match!");
//            }
//            model.addAttribute("user", userDto);
//            return new ModelAndView("fragments/register", "user", userDto);
//        } else {
//
//            User registered = userService.registerNewUser(userDto);
//
//            if (registered == null) {
//                bindingResult.rejectValue("email", "message.regError");
//            }
//
//            if (bindingResult.hasErrors()) {
//                return new ModelAndView("fragments/register", "user", userDto);
//            } else {
//
//                try {
//                    String appUrl = httpServletRequest.getProtocol() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
//                    eventPublisher.publishEvent(new OnRegisterCompletedEvent
//                            (registered, httpServletRequest.getLocale(), appUrl));
//                } catch (Exception me) {
//                    bindingResult.rejectValue("email", "message.regError");
//                    return new ModelAndView("fragments/register", "user", userDto);
//                }
//
//                model.addAttribute("user", registered);
//                return new ModelAndView("fragments/confirmation-email", "user", registered);
//            }
//
//        }


    }

}
