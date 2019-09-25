package com.otto.nycschools.Model;

public class Schools {

    private String borough, school_name,dbn;


    public Schools(String school_name, String borough, String dbn){

        this.school_name = school_name;
        this.borough = borough;
        this.dbn = dbn;

    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String mBorough) {
        this.borough = mBorough;
    }

    public String getSchoolName() {
        return school_name;
    }

    public void setSchoolName(String mSchoolName) {
        this.school_name = mSchoolName;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

}
