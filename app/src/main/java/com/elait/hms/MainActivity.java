/*package com.elait.hms;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.elait.hms.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    //    SearchView searchView =
    //            (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //searchView.setSearchableInfo(
        //        searchManager.getSearchableInfo(getComponentName()));
        //ComponentName cn = new ComponentName(this, SearchResultsActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.send_sms:
                Toast.makeText(view.getContext(), "SMS Button Pressed!",
                        Toast.LENGTH_LONG).show();
        }
    }
}
*/
package com.elait.hms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static com.elait.hms.R.id.name;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> stringArrayList;
    private SearchListViewAdapter adapter;
    DatabaseHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    EditText txtphoneNo, txtname;
    int i ;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new DatabaseHelper(getApplicationContext());
        TableLayout tl = (TableLayout) findViewById(R.id.table_layout_notification);
        helper = new DatabaseHelper(getApplicationContext());
        db = helper.getReadableDatabase();
        cursor = helper.getpatientaddhistorydetails(db);
        txtphoneNo = (EditText) findViewById(R.id.p_phone);
        txtname = (EditText) findViewById(R.id.p_name);
        TableRow tr_head = new TableRow(this);
        //tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView label_name = new TextView(this);
        // label_name.setId(20);
        label_name.setText("NAME");
        label_name.setTextColor(Color.BLACK);
        label_name.setPadding(1, 1, 1, 1);
        tr_head.addView(label_name);// add the column to the table row here

        TextView label_contact = new TextView(this);
        //label_contact.setId(23);// define id that must be unique
        label_contact.setText("CONTACT"); // set the text for the header
        label_contact.setTextColor(Color.BLACK); // set the color
        label_contact.setPadding(2, 2, 2, 2); // set the padding (if required)
        tr_head.addView(label_contact); // add the column to the table row here

        TextView label_Last_visit = new TextView(this);
        //label_Last_visit.setId(7);// define id that must be unique
        label_Last_visit.setText("LASTVISIT"); // set the text for the header
        label_Last_visit.setTextColor(Color.BLACK); // set the color
        label_Last_visit.setPadding(3, 3, 3, 3); // set the padding (if required)
        tr_head.addView(label_Last_visit);

        TextView label_Next_visit = new TextView(this);
        label_Next_visit.setId(View.generateViewId());// define id that must be unique
        label_Next_visit.setText("NEXTVISIT"); // set the text for the header
        label_Next_visit.setTextColor(Color.BLACK); // set the color
        label_Next_visit.setPadding(2, 2, 2, 2); // set the padding (if required)
        tr_head.addView(label_Next_visit);

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        Integer count = 0;


        if (cursor.moveToFirst()) {
            do {
                TableRow tr = new TableRow(this);

                final String join_name, join_contact_no, join_Last_visit, join_Next_visit;
                join_name = cursor.getString(0);
                join_contact_no = cursor.getString(1);
                join_Last_visit = cursor.getString(3);
                join_Next_visit = cursor.getString(2);


                if (count % 2 != 0) tr.setBackgroundColor(Color.GRAY);
                tr.setId(100 + count);
                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));


//Create two columns to add as table data
                // Create a TextView to add date

                TextView labelname = new TextView(this);
                labelname.setId(200 + count);
                labelname.setText(join_name);
                labelname.setPadding(2, 0, 5, 0);
                labelname.setTextColor(Color.BLACK);
                tr.addView(labelname);
                TextView labelcontact = new TextView(this);
                labelcontact.setId(200 + count);
                labelcontact.setText(join_contact_no.toString());

                labelcontact.setTextColor(Color.BLACK);
                tr.addView(labelcontact);
                TextView labellast_visit = new TextView(this);
                labellast_visit.setId(200 + count);
                labellast_visit.setText(join_Last_visit.toString());

                labellast_visit.setTextColor(Color.BLACK);
                tr.addView(labellast_visit);
                labelname.setPadding(2, 0, 5, 0);
                TextView labelnext_visit = new TextView(this);
                labelnext_visit.setId(200 + count);
                labelnext_visit.setText(join_Next_visit.toString());

                labelnext_visit.setTextColor(Color.BLACK);
                tr.addView(labelnext_visit);

                CheckBox cb = new CheckBox(this);
                cb.setChecked(false);

                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

                        if (isChecked) {

                            try {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(join_contact_no, null, "Hello " + join_name + " your appointment is on " + join_Next_visit, null, null);
                                Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                                Log.d("---", "Sent");

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                                Log.d("---", "Fail");
                                e.printStackTrace();
                            }

                        }
                    }


                });

                tr.addView(cb);


// finally add this to the table row

                tl.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                count++;

            } while (cursor.moveToNext());
            cursor.close();
        }

        listView = (ListView) findViewById(R.id.search_list_item);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                // int i = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, TabsActivity.class);
                String[] parts = ((String) parent.getItemAtPosition(position)).split(" ");
                cursor=helper.getPatientDetails(parts[0],parts[1],parts[2]);
                cursor.moveToFirst();
                intent.putExtra("PATIENT_ID",cursor.getInt(0));
                intent.putExtra("PATIENT_NAME",parts[0]);//getText(1)
                intent.putExtra("MOBILE_NO",parts[1]);
                intent.putExtra("AGE",parts[2]);
                //getText[3]
                //get Gender and Age in ann format where a is 'M' or 'F' and nn is the age(00-990.
                //For example a 13 years old girl will be shown as F13
                //The top displat on TabView will be "Prithik" "M10" "9090901234" "15", where 15 is the Patient ID.
                startActivity(intent);
            }
        });
        listView.setVisibility(View.INVISIBLE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getData() {
        if (stringArrayList != null) {
            stringArrayList.clear();
        } else {
            stringArrayList = new ArrayList<>();
        }
        if (cursor.moveToFirst()) {
            do {


                String name,contact_no,age;
                name = cursor.getString(0);
                contact_no = cursor.getString(1);
                age = cursor.getString(2);
                String contactDetails=name+" "+contact_no+" "+age;
                stringArrayList.add(contactDetails);

            } while (cursor.moveToNext());
        }
        //cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        listView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Search button clicked", Toast.LENGTH_SHORT).show();
                helper = new DatabaseHelper(getApplicationContext());
                db = helper.getReadableDatabase();
                cursor = helper.getadddetails(db);
                getData();
                adapter = new SearchListViewAdapter(this, R.layout.search_item_listview, stringArrayList);
                listView.setAdapter(adapter);
                if (cursor.moveToFirst()) {
                    do {


                        String name,contact_no,age;
                        name = cursor.getString(0);
                        contact_no = cursor.getString(1);
                        age = cursor.getString(2);
                        String contactDetails=name+" "+contact_no+" "+age;
                        stringArrayList.add(contactDetails);

                    } while (cursor.moveToNext());
                }
                cursor.close();
                return true;
            case R.id.action_add_person:
                Toast.makeText(getApplicationContext(), "Add button Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddNewPatient.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                Toast.makeText(getApplicationContext(), "Home button Clicked", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(in);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}