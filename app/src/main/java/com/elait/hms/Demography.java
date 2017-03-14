package com.elait.hms;

/**
 * Created by User on 2017-03-02.
 */

public class Demography {

    String  diabties,smoking,Blood_pressure,Address, Emerg_no;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmerg_no() {
        return Emerg_no;
    }

    public void setEmerg_no(String emerg_no) {
        Emerg_no = emerg_no;
    }

    public String getDiabties() {
        return diabties;
    }

    public void setDiabties(String diabties) {
        this.diabties = diabties;
    }

    public String getSmoking() {

        return smoking;
    }

    public void setSmoking(String smoking) {

        this.smoking = smoking;
    }

    public String getBlood_pressure() {
        return Blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {

        Blood_pressure = blood_pressure;
    }
}

