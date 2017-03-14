package com.elait.hms;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 2017-02-23.
 */

public class TabDemography extends android.support.v4.app.Fragment implements View.OnClickListener {
    DatabaseHelper Helper;
    SQLiteDatabase sqLiteDatabase;
    EditText SH,HTH,DH, Emergency_no, Address;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab_demography, container, false);
        Helper  = new DatabaseHelper(getActivity().getApplicationContext());

        sqLiteDatabase = Helper.getWritableDatabase();
        String age = getActivity().getIntent().getStringExtra("AGE");
        String gender = getActivity().getIntent().getStringExtra("GENDER");

        Button one = (Button) view.findViewById(R.id.SAVE);
        one.setOnClickListener(this);
        TextView tv_age = (TextView) view.findViewById(R.id.age);
        TextView tv_gender = (TextView) view.findViewById(R.id.gender);
        SH = (EditText) view.findViewById(R.id.sh);
        HTH = (EditText) view.findViewById(R.id.hth);
        DH = (EditText) view.findViewById(R.id.dh);
        Emergency_no = (EditText) view.findViewById(R.id.emergency_no);
        Address = (EditText) view.findViewById(R.id.address);

        tv_gender.setText(gender);
        tv_age.setText(age);
        return view;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.SAVE) {


            String SH_str = SH.getText().toString().trim();
            String HTH_str = HTH.getText().toString().trim();
            String DH_str = DH.getText().toString().trim();
            String Emergency_str = Emergency_no.getText().toString().trim();
            String Address_str = Address.getText().toString().trim();

            Demography D = new Demography();
            D.setSmoking(SH_str);
            D.setBlood_pressure(HTH_str);
            D.setDiabties(DH_str);
            D.setEmerg_no(Emergency_str);
            D.setAddress(Address_str);

            Helper.insertdemographytRecord(D);
            Toast.makeText(getActivity().getBaseContext(), "Data inserted Successfully", Toast.LENGTH_LONG).show();

        }
    }
}
