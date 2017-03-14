package com.elait.hms;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.id;
import static com.elait.hms.R.id.patient_id;

/**
 * Created by User on 2017-02-23.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String PATIENT_CONTACTS = "contacts";
    public static final String PATIENT_RECORDS = "patient_history";
    public static final String COLUMN_patient_id = "id";
    public static final String COLUMN_age = "age";
    public static final String COLUMN_contact_no = "contact_no";
    public static final String COLUMN_First_name = "fname";
    public static final String COLUMN_Last_name = "lname";
    public static final String COLUMN_gender = "gender";
    public   static  final  String COLUMN_Address="address";
    public   static  final  String COLUMN_Emerg_contact="emergency_contactno";
    public   static  final  String COLUMN_patient_history_id="patient_id";
    public   static  final  String PATIENT_DEMOGRAPHY="demography";
    public static final String COLUMN_DH = "diabties";
    public static final String COLUMN_SH = "smoking";
    public static final String COLUMN_BPH = "Blood_pressur";
    public static final String COLUMN_pres = "prescription";
    public static final String COLUMN_diagnosis = "diagnosis";
    public static final String COLUMN_note = "note";
    public static final String COLUMN_next_visit = "next_visit";
    public static final String COLUMN_date = "date";

    SQLiteDatabase db;


    public static final String CONTACT_CREATE = "create table contacts (id integer primary key AUTOINCREMENT , "
            + "fname text not null , lname text not null , age text not null , date text not null , contact_no text not null , gender text);";

    public static final String HISTORY_CREATE = "create table patient_history (id integer primary key not null, patient_id integer , "
            + "prescription text not null , diagnosis text not null , note text not null , next_visit text not null , date Date default Current_timestamp);";

    public static final String DEMOGRAPHY_CREATE="create table demography(demography_id integer primary key AUTOINCREMENT not null , diabities text" +
            " , smoking text , Blood_pressur text , address text not null , emergency_contactno , FOREIGN KEY(demography_id) REFERENCES Patient_details(id) );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACT_CREATE);
        db.execSQL(DEMOGRAPHY_CREATE);
        db.execSQL(HISTORY_CREATE);
        this.db = db;
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {

        db.setForeignKeyConstraintsEnabled(true);
    }

    public void insertcontacts(Contact c) {
        db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_age, c.getage());
        value.put(COLUMN_contact_no, c.getContact_no());
        value.put(COLUMN_First_name, c.getFname());
        value.put(COLUMN_Last_name, c.getLname());
        value.put(COLUMN_date, c.getDate());
        value.put(COLUMN_gender, c.getDate());
        db.insert(PATIENT_CONTACTS, null, value);
    }

    public void insertPatientRecord(Patient_Records c) {
        db = this.getWritableDatabase();
        ContentValues value1 = new ContentValues();
        value1.put(COLUMN_patient_history_id,c.getId());
        value1.put(COLUMN_diagnosis, c.getDiagnosis());
        value1.put(COLUMN_pres, c.getPrescription());
        value1.put(COLUMN_note, c.getNotes());
        value1.put(COLUMN_next_visit, c.getNext_visit());
        value1.put(COLUMN_date, c.getDate());
        db.insert(PATIENT_RECORDS, null, value1);
    }

    public void insertdemographytRecord(Demography D){
        db = this.getWritableDatabase();
        ContentValues value_d = new ContentValues();
        value_d.put(COLUMN_SH,D.getSmoking());
        value_d.put(COLUMN_BPH,D.getBlood_pressure());
        value_d.put(COLUMN_DH,D.getDiabties());
        value_d.put(COLUMN_Address,D.getAddress());
        value_d.put(COLUMN_Emerg_contact,D.getEmerg_no());
        db.insert(PATIENT_DEMOGRAPHY,null,value_d);
    }

    public Cursor getadddetails(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {COLUMN_First_name,COLUMN_contact_no,COLUMN_age};
        cursor = db.query(PATIENT_CONTACTS, projections, null, null, null, null, null);
        return cursor;
    }



  /* public Cursor getPatientHistory(SQLiteDatabase db,String id){
        Cursor cursor;
        String[] projections1={COLUMN_date,COLUMN_pres,COLUMN_diagnosis,COLUMN_note};
        cursor=db.query(PATIENT_RECORDS,projections1,COLUMN_patient_history_id+"=?",new String[] {id},null,null,COLUMN_date+" DESC");
        return cursor;
    }*/


    private final String Query_2 = " SELECT h.date,h.prescription,h.diagnosis,h.note FROM patient_history h , contacts c WHERE h.patient_id = c.id ";

    private final String Query = " SELECT h.date,h.prescription,h.diagnosis,h.note FROM patient_history h WHERE h.patient_id = ? ORDER BY h.date DESC ";

    public Cursor getPatientHistory_1(String id){
        String[] params=new String[]{id};
        Cursor cursor = getReadableDatabase().rawQuery(Query, params);
        Log.i("my_query",Query);
        return cursor;
    }
    public Cursor getPatientHistory(SQLiteDatabase db) {
        Cursor cursor;
        cursor = db.rawQuery(Query, null);
        Log.i("my_query",Query);
        return cursor;
    }

    private final String MY_QUERY = "SELECT contacts.fname,contacts.contact_no,patient_history.next_visit,patient_history.date,patient_history.patient_id " +
            "FROM contacts INNER JOIN patient_history ON contacts.id = patient_history.patient_id ";

    public Cursor getpatientaddhistorydetails(SQLiteDatabase db) {
        Cursor cursor;
        cursor = db.rawQuery(MY_QUERY, null);
        return cursor;
    }
    private final String GET_CONTACT_QUERY = "SELECT * from contacts c where fname=? and contact_no=? and age=?";

    public Cursor getPatientDetails(String fName, String contactNo , String age){
        int id;
        String[] params=new String[]{fName,contactNo,age};
        Cursor cursor = getReadableDatabase().rawQuery(GET_CONTACT_QUERY, params);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        String query = "DROP TABLE if exist " + PATIENT_CONTACTS;
        String query1 = "DROP TABLE if exist " + PATIENT_RECORDS ;
        String query2 = "DROP TABLE if exist " + PATIENT_DEMOGRAPHY ;
        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        this.onCreate(db);

    }
}
