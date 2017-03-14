package com.elait.hms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 2017-02-23.
 */

public class Patient_Records {
    String Notes ,prescription, diagnosis, next_visit,id;

    public String getNext_visit() {
        return next_visit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNext_visit(String next_visit) {
        this.next_visit = next_visit;
    }

    public void setDiagnosis(String diagnosis) {

        this.diagnosis = diagnosis;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDate(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date=new Date();
        return simpleDateFormat.format(date);

    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}

