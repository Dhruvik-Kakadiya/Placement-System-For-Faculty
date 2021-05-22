package com.example.dduplacementadmin;

public class Company_Helper {

        public String name,type,tech,role,company_package,bond,lastDate,comeDate,cgpi_above;

    public Company_Helper(String name, String type, String tech, String role, String company_package, String bond, String lastDate, String comeDate,String cgpi_above) {
        this.name = name;
        this.type = type;
        this.tech = tech;
        this.role = role;
        this.company_package = company_package;
        this.bond = bond;
        this.lastDate = lastDate;
        this.comeDate = comeDate;
        this.cgpi_above = cgpi_above;
    }

    public Company_Helper() {
    }

    public String getCgpi_above() {
        return cgpi_above;
    }

    public void setCgpi_above(String cgpi_above) {
        this.cgpi_above = cgpi_above;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getComeDate() {
        return comeDate;
    }

    public void setComeDate(String comeDate) {
        this.comeDate = comeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCompany_package() {
        return company_package;
    }

    public void setCompany_package(String company_package) {
        this.company_package = company_package;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }
}
