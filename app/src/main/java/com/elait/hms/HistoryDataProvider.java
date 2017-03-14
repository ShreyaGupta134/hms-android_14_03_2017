package com.elait.hms;

/**
 * Created by Shreya Gupta on 25-02-2017.
 */

public class HistoryDataProvider {
    private String id;
    private String prescription,note,date,diagnosis;



    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getDiagnosis() {

        return diagnosis;
    }

    public void setDiagnosis(String diagnosis)

    {

        this.diagnosis = diagnosis;
    }



    public String getPrescription() {

        return prescription;
    }

    public void setPrescription(String prescription) {

        this.prescription = prescription;
    }

    public String getNote() {

        return note;
    }

    public void setNote(String note) {

        this.note = note;
    }


    public String getDate() {
        return date;

    }

    public void setDate(String date) {

        this.date = date;
    }

    public HistoryDataProvider(String prescription, String diagnosis , String note, String date) {
        this.prescription = prescription;
        this.diagnosis =diagnosis;
        this.note = note;
        this.date = date;
    }

}

