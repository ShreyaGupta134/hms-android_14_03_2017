package com.elait.hms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-02-24.
 */

public class HistoryListDataAdapter extends ArrayAdapter {


    List list= new ArrayList();


    public HistoryListDataAdapter(Context context, int resource, List<String> strings) {
        super(context, resource);
    }

    static class Listhandler {
        TextView date,details,id;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Listhandler lh;
        if (row == null) {
            LayoutInflater lf = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = lf.inflate(R.layout.tab_view_records, parent, false);
            lh = new Listhandler();
            lh.details = (TextView) row.findViewById(R.id.patient_data);
            lh.date = (TextView) row.findViewById(R.id.date);
            row.setTag(lh);

        }
        else
        {
            lh = (Listhandler) row.getTag();
        }

        HistoryDataProvider dp = (HistoryDataProvider) this.getItem(position);
        lh.details.setText("Diagnosis: "+dp.getNote() +"\n Prescription: "+dp.getPrescription()+"\n Notes: "+dp.getDiagnosis());
        lh.date.setText(dp.getDate());
        return row;
    }
}




