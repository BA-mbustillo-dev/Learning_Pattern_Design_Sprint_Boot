/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patron.patron.chain.responsability.method.domain;

/**
 *
 * @author mbustillo
 */
public abstract class Contributor {
    
    private String name;
    private String rfc;
    private Status status;
    
    private Address addres;
    private Telephone telephone;
    private CreditData creditData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddres() {
        return addres;
    }

    public void setAddres(Address addres) {
        this.addres = addres;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public CreditData getCreditData() {
        return creditData;
    }

    public void setCreditData(CreditData creditData) {
        this.creditData = creditData;
    }
    
    
}
