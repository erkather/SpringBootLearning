package com.example.BusBookingApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class UserInfo {

    @Id
    @SequenceGenerator(
            name = "user_info_sequence",
            sequenceName = "user_info_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.SEQUENCE,
            generator = "user_info_sequence"
    )
    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String fullname;
    private String email;
    private String role;
    private String password;
    private LocalDate dob;

    public UserInfo()
    {
    }

    public UserInfo(String name, String fullname, String email, String role, String password, LocalDate dob)
    {
        this.name = name;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.dob = dob;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LocalDate getDob()
    {
        return dob;
    }

    public void setDob(LocalDate dob)
    {
        this.dob = dob;
    }
}
