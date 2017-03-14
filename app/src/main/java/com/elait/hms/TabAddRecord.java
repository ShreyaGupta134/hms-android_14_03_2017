package com.elait.hms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by balasubramanian on 02/02/17.
 */

public class TabAddRecord extends android.support.v4.app.Fragment implements View.OnClickListener{

    EditText p_id ;
    EditText p_notes;
    EditText p_diagnosis;
    EditText p_prescription ;
    EditText p_next_visit;
    Button add_btn ;

    DatabaseHelper helper ;
    //  SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_add_record, container, false);

        add_btn=(Button)view.findViewById(R.id.AddBtn);
        add_btn.setOnClickListener(this);
        helper = new DatabaseHelper(this.getActivity());

        return  view;
    }

    @Override
    public void onClick(View v) {

        p_id = (EditText) getView().findViewById(R.id.patient_id);
        p_notes = (EditText) getView().findViewById(R.id.notes);
        p_diagnosis = (EditText) getView().findViewById(R.id.diagnosis);
        p_prescription = (EditText) getView().findViewById(R.id.prescription);
        p_next_visit = (EditText) getView().findViewById(R.id.next_visit);

        String patient_id = p_id.getText().toString();
        String patient_notes = p_notes.getText().toString();
        String patient_diagnosis = p_diagnosis.getText().toString();
        String patient_pres= p_prescription.getText().toString();
        String patient_reporting= p_next_visit.getText().toString();


        Patient_Records c = new Patient_Records();

        c.setId(patient_id);
        c.setNotes(patient_notes);
        c.setDiagnosis(patient_diagnosis);
        c.setPrescription(patient_pres);
        c.setNext_visit(patient_reporting);

        helper.insertPatientRecord(c);

        p_id.setText(" ");
        p_notes.setText(" ");
        p_diagnosis.setText(" ");
        p_prescription.setText(" ");
        p_next_visit.setText(" ");

        Toast.makeText(getActivity(), "one record inserted", Toast.LENGTH_LONG).show();
    }
}