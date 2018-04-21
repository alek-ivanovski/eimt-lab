package com.eimt.lab.web;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.FormEmployee;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("employee", new FormEmployee());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegistrationForm(@ModelAttribute("employee") @Valid FormEmployee formEmployee,
                                       BindingResult bindingResult) {
        if(!bindingResult.hasErrors())
            employeeService.saveEmployee(formEmployee);
        return "register";
    }

}
