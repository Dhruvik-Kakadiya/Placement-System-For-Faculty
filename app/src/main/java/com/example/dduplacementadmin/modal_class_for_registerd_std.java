package com.example.dduplacementadmin;

public class modal_class_for_registerd_std {

    String Email,CollageID;

    public modal_class_for_registerd_std() {
    }

    public modal_class_for_registerd_std(String email, String collageID) {
        Email = email;
        CollageID = collageID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCollageID() {
        return CollageID;
    }

    public void setCollageID(String collageID) {
        CollageID = collageID;
    }
}
