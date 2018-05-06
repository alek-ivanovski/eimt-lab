package com.eimt.lab.registration;

import com.eimt.lab.model.Employee;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegisterCompletedEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private Employee employee;

    public OnRegisterCompletedEvent(
            Employee employee, Locale locale, String appUrl) {
        super(employee);

        this.employee = employee;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
