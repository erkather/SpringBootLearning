package com.example.BusBookingApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class BusOperator {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long operatorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "operator_id", referencedColumnName = "userId", nullable = false)
    private UserInfo userInfo;

    @Column(unique = true, nullable = false)
    private String companyName;
    @Column(unique = true, nullable = false)
    private String contactNumber;
    private String address;

    public BusOperator()
    {
    }

    public BusOperator(UserInfo userInfo, String companyName, String contactNumber, String address)
    {
        this.userInfo = userInfo;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
