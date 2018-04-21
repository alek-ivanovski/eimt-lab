package com.eimt.lab.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class FormEmployee {

    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @Size(min = 6, max = 20)
    private String confirmPassword;

    @NotNull
    @Max(30)
    private String firstName;

    @NotNull
    @Max(30)
    private String lastName;

    @NotNull
    private String gender;

    @NotNull
    private CharSequence birthDate;

    public FormEmployee() {
    }

    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CharSequence getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(CharSequence birthDate) {
        this.birthDate = birthDate;
    }
}
