package com.elait.hms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import static android.R.attr.id;
import static com.elait.hms.DatabaseHelper.COLUMN_date;
import static com.elait.hms.DatabaseHelper.COLUMN_diagnosis;
import static com.elait.hms.DatabaseHelper.COLUMN_note;
import static com.elait.hms.DatabaseHelper.COLUMN_patient_history_id;
import static com.elait.hms.DatabaseHelper.COLUMN_pres;
import static com.elait.hms.DatabaseHelper.PATIENT_RECORDS;

/**
 * Created by Shreya Gupta on 25-02-2017.
 */
// DataListView Activity for Displaying the Records of pre registered patients.

public class TabViewRecordsHistory extends android.support.v4.app.Fragment {
    ListView listView;
    SQLiteDatabase db;
    DatabaseHelper helper;
    Cursor cursor;

    HistoryListDataAdapter history_listDataAdapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_activity_data_list_view_activity, container, false);
        listView = (ListView) rootView.findViewById(R.id.List_view_history);
        helper = new DatabaseHelper(getActivity().getApplicationContext());
        db = helper.getReadableDatabase();
        //String id_history = getActivity().getIntent().getStringExtra("PATIENT_ID");
        String id_history = Integer.toString(getActivity().getIntent().getIntExtra("PATIENT_ID",-1));
        cursor=helper.getPatientHistory_1(id_history);
        //cursor=helper.getPatientHistory(db);
        history_listDataAdapter = new HistoryListDataAdapter(getActivity().getApplicationContext(), R.layout.tab_view_records, null);
        listView.setAdapter(history_listDataAdapter);


        if (cursor.moveToFirst()) {

            do {
                String prescription,notes,diagnosis,date;

                date = cursor.getString(0);
                prescription = cursor.getString(1);
                diagnosis = cursor.getString(2);
                notes = cursor.getString(3);

                HistoryDataProvider dataProvider = new HistoryDataProvider(prescription,notes,diagnosis,date);
                history_listDataAdapter.add(dataProvider);
            } while (cursor.moveToNext());
        }
        return rootView;

    }
}
