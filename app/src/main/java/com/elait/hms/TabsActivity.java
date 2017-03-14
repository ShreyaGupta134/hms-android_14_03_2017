package com.elait.hms;

/**
 * Created by balasubramanian on 02/02/17.
 */


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        String name = getIntent().getStringExtra("PATIENT_NAME");
        String phn = getIntent().getStringExtra("MOBILE_NO");
        String age = getIntent().getStringExtra("AGE");

        int id=getIntent().getIntExtra("PATIENT_ID",-1);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
/*        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab3_title));*/
        TextView tv_name = (TextView) findViewById(R.id.name);
        TextView tv_phone = (TextView) findViewById(R.id.mobile_no);
        TextView tv_age = (TextView) findViewById(R.id.age);
        TextView tv_id=(TextView) findViewById(R.id.id);

        tv_name.setText(name);
        tv_phone.setText(phn);
        tv_id.setText(Integer.toString(id));
        tv_age.setText(age);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1_title).setIcon(R.drawable.ic_personal_information));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2_title).setIcon(R.drawable.ic_add_patient_record));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab3_title).setIcon(R.drawable.ic_view_patient_records));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

  /*      tabLayout.setSelectedTabIndicatorColor(getColor(R.color.selected_tab_indicator_color));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),R.color.selected_tab_indicator_color);*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //  if (id == R.id.action_settings) {
        //      return true;
        //   }

        return super.onOptionsItemSelected(item);
    }
}