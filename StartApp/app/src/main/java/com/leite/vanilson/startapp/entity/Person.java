package com.leite.vanilson.startapp.entity;

import java.util.Date;

/**
 * Created by Vanilson on 19/12/2017.
 */

public class Person {
    private int id;
    private String name, address, cpfCnpj;
    private PersonType personType;
    private Gender gender;
    private Date dtBirthday;
    private Profession profession;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDtBirthday() {
        return dtBirthday;
    }

    public void setDtBirthday(Date dtBirthday) {
        this.dtBirthday = dtBirthday;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", personType=" + personType +
                ", gender=" + gender +
                ", dtBirthday=" + dtBirthday +
                ", profession=" + profession +
                '}';
    }
}
