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

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        System.out.println("register controller get");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegistrationForm(@ModelAttribute("employee") @Valid FormEmployee formEmployee,
                                       BindingResult bindingResult) {
        System.out.println("register controller post");
//        TODO: Fix the NullPointerException that occurs on the next line!
        System.out.println("Employee name:" + formEmployee.getFirstName());
        Employee employee = employeeService.saveEmployee(formEmployee);
        return "register";
    }

}
