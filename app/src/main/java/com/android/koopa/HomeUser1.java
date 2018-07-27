package com.android.koopa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeUser1 extends BaseClass {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user1);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.searchb) ;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in=new Intent(getApplicationContext(),ActivitySearchScreen.class);
               startActivity(in);
            }
        });

//        0
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_user1, menu);
        return true;
    }

    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),LoginPage.class);
        Logout();
        startActivity(in);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_register:
                Toast.makeText(getApplicationContext(),"register",Toast.LENGTH_LONG).show();

                Intent in2= new Intent(getApplicationContext(),Registration.class);
                startActivity(in2);
                break;
            case R.id.passhome:
                Intent inpass= new Intent(getApplicationContext(),HomeUser1.class);
                startActivity(inpass);

                break;
            case R.id.signoutoption:
                FirebaseAuth.getInstance().signOut();
                SharedPreferance p=new SharedPreferance(getApplicationContext());
                boolean ans=Logout();
                if(ans){
                    Intent log= new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(log);
                }
                else {
                    Toast.makeText(getApplicationContext(),"something goes wrong",Toast.LENGTH_SHORT);
                }


                break;

        }

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**mkl;jkjkjkjk
     *
     *
     *
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home_user1, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:FragPoster farg=new FragPoster();
                return farg;
                case 1:FragPostAdd frag=new FragPostAdd();
                return frag;
                case 2:
                    SharedPreferences sp=getApplicationContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    String type=sp.getString("Type","");
                    if(type=="User"){
                        FragUserNoti fragUserNoti=new FragUserNoti();
                        return fragUserNoti;
                    }
                    else{
                    FragNotification fragNotification=new FragNotification();
                        return fragNotification;
                    }


                case 3:FragUserHome fragNote=new FragUserHome();
                    FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();

                    // Check if user is signed in (non-null) and update UI accordingly.
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if(currentUser!=null){
                        return fragNote;

                    }
                    else {
                        Intent passin = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(passin);
                    }
                    }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }
    public boolean Logout(){
        SharedPreferences sp=getApplicationContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("Uid");
        editor.remove("Email");
        editor.remove("Password");
        editor.remove("Name");
        editor.remove("Category");
        editor.remove("Type");
        editor.remove("Workno");
        editor.remove("District");
        editor.remove("ImageUrl");
        editor.remove("Subdistrict");
        editor.commit();
        return true;
    }

}
