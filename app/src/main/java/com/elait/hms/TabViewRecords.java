package com.elait.hms;

/**
 * Created by balasubramanian on 02/02/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabViewRecords extends Fragment {

    public final String HistoryCollection[][] = new String[][]{
            {"Vikram", "9432088774", "12/03/2015", "Diagnosis: Cardiac Failure  \n\n Prescription: don't run \n\n Notes: Nil"},
            {"Jyotsna", "8904454741", "17/03/2016", " Diagnosis: Cardiac Arrest  \n\n Prescription: don't take stress  \n\n Notes: Bypass surgery"},
            {"Randeep", "7878051445", "13/04/2017", "Diagnosis: kidney failure  \n\n Prescription: Dialysis  \n\n Notes: drink less water"},
            {"Chander", "9787475200", "25/08/1993", "Diagnosis: cold  \n\n Prescription: steam   \n\n Notes: Be away from cold things "},
            {"Archana", "9901232547", "21/08/2009", "Diagnosis: fever  \n\n Prescription: Nil  \n\n Notes: Nil"}
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_view_records, container, false);
        String diagnosis = getActivity().getIntent().getStringExtra("DIAGNOSIS");
        String prescription = getActivity().getIntent().getStringExtra("PRESCRIPTION");
        String notes = getActivity().getIntent().getStringExtra("NOTES");
        String reporting = getActivity().getIntent().getStringExtra("REPORTING");


        TextView details = (TextView) view.findViewById(R.id.patient_data);
        details.setText("Diagnosis: "+ diagnosis +"\n Prescription: "+ prescription +"\n Notes: "+ notes );

        /*for (String[] p_dg : HistoryCollection) {

            if (p_dg[0].equals(name) && p_dg[1].equals(phn)) {
                TextView textView = (TextView) view.findViewById(R.id.patient_data);
                textView.setText(p_dg[3]);
                TextView textView_date = (TextView) view.findViewById(R.id.date);
                textView_date.setText(p_dg[2]);
            }
        }*/


        return view;
    }
}