package com.elait.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by User on 2017-02-23.
 */

public class AddNewPatient extends Activity{
    DatabaseHelper Helper = new DatabaseHelper(this);
    RadioGroup rg ;
    RadioButton rb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient_layout);
        rg =(RadioGroup) findViewById(R.id.rgroup);

        EditText fname = (EditText) findViewById(R.id.p_name);
        EditText lname = (EditText) findViewById(R.id.p_name_last);
        EditText age = (EditText) findViewById(R.id.p_age);
        EditText phone = (EditText) findViewById(R.id.p_phone);
        EditText date = (EditText) findViewById(R.id.p_date);


       // id.addTextChangedListener(textWatcher);
        fname.addTextChangedListener(textWatcher);
        lname.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);

        checkFieldsForEmptyValues();

    }

    public  void rbclick (View vw) {

        int radiobuttonclick = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(radiobuttonclick);
        if (rb != null) {
            Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    public void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.P_register);
      //  EditText id = (EditText) findViewById(R.id.p_id);
        EditText fname = (EditText) findViewById(R.id.p_name);
        EditText lname = (EditText) findViewById(R.id.p_name_last);
        EditText age = (EditText) findViewById(R.id.p_age);
        EditText phone = (EditText) findViewById(R.id.p_phone);
        EditText date = (EditText) findViewById(R.id.p_date);


        //String id_str = id.getText().toString();
        String fname_str = fname.getText().toString();
        String lname_str = lname.getText().toString();
        String age_str = age.getText().toString();
        String phone_str = phone.getText().toString();
        String date_str = date.getText().toString();
        if(fname_str.equals("")||lname_str.equals("")||age_str.equals("")||phone_str.equals("")||date_str.equals("")){
            b.setEnabled(true);
        }
        else {
            b.setEnabled(true);
        }
    }
    public void OnRegisterClick (View v) {

        if (v.getId() == R.id.P_register) {

          //  EditText id = (EditText) findViewById(R.id.p_id);
            String  male_female="";

            EditText fname = (EditText) findViewById(R.id.p_name);
            EditText lname = (EditText) findViewById(R.id.p_name_last);
            EditText age = (EditText) findViewById(R.id.p_age);
            EditText phone = (EditText) findViewById(R.id.p_phone);
            EditText date = (EditText) findViewById(R.id.p_date);
            int radiobuttonclick = rg.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById (radiobuttonclick);

         //   id.addTextChangedListener(textWatcher);
            fname.addTextChangedListener(textWatcher);
            lname.addTextChangedListener(textWatcher);
            age.addTextChangedListener(textWatcher);
            phone.addTextChangedListener(textWatcher);
            date.addTextChangedListener(textWatcher);

            //  String id_str = id.getText().toString().trim();
            String fname_str = fname.getText().toString().trim();
            String lname_str = lname.getText().toString().trim();
            String age_str = age.getText().toString().trim();
            String phone_str = phone.getText().toString().trim();
            String date_str = date.getText().toString().trim();

            if(rb!=null)
            {   male_female = rb.getText().toString();

            }

            checkFieldsForEmptyValues();
            Contact c = new Contact();
           // c.setId(id_str);

            c.setgender(male_female);
            c.setage(age_str);
            c.setFname(fname_str);
            c.setLname(lname_str);
            c.setContact_no(phone_str);
            c.setDate(date_str);

            Helper.insertcontacts(c);
            Toast.makeText(getBaseContext(), "Data inserted Successfully", Toast.LENGTH_LONG).show();
            Intent i=new Intent(AddNewPatient.this,TabsActivity.class);
            i.putExtra("NEXT_TAB", "Demography");
            i.putExtra("NAME",fname_str);
            i.putExtra("CONTACT_NO",phone_str);
            i.putExtra("GENDER",male_female);
            i.putExtra("AGE",age_str);
            startActivity(i);
        }
    }

    public void OnExitClick (View v){
        if (v.getId() == R.id.exit_button){
            Intent i = new Intent(AddNewPatient.this,MainActivity.class);
            startActivity(i);
        }
    }
}
