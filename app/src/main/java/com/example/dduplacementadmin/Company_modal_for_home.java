package com.example.dduplacementadmin;

public class Company_modal_for_home {

    String name,tech,role,type,company_package, bond,comeDate, lastDate;


    public Company_modal_for_home(String name, String tech, String role, String type, String company_package, String bond, String comeDate, String lastDate) {
        this.name = name;
        this.tech = tech;
        this.role = role;
        this.type = type;
        this.company_package = company_package;
        this.bond = bond;
        this.comeDate = comeDate;
        this.lastDate = lastDate;
    }

    public String getComeDate() {
        return comeDate;
    }

    public void setComeDate(String comeDate) {
        this.comeDate = comeDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Company_modal_for_home() {
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany_package() {
        return company_package;
    }

    public void setCompany_package(String company_package) {
        this.company_package = company_package;
    }
}
