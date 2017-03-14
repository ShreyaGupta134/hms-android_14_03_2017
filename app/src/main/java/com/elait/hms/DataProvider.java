package com.elait.hms;

/**
 * Created by User on 2017-03-02.
 */

public class DataProvider {



    private String name;

    private String Contact_no,last_visit;


    public String getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(String last_visit) {
        this.last_visit = last_visit;
    }

    public String getContact_no() {


        return Contact_no;

    }

    public void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }



    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public DataProvider(String name, String Contact_no,String last_visit){

        this.name=name;
        this.Contact_no=Contact_no;
        this.last_visit=last_visit;

    }




}