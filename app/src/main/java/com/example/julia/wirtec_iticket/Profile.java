package com.example.julia.wirtec_iticket;
import android.support.v4.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.julia.wirtec_iticket.EventsAttended;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    EventsAttended eventsAttended;
    MainProfile mainProfile;
    ArrayList<String> data;
    public FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton editprofile, logout;
    private Account c;


//    @Override
//    protected void onResume() {
//        super.onResume();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                setTitle(dataSnapshot.getKey());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
////        setTitle(c.getName());
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EventParcelable ep = getIntent().getParcelableExtra("event");

        mainProfile = new MainProfile();
        //mainProfile.setEvent(ep);
        // setTitle(ep.getEventname());
        eventsAttended = new EventsAttended();
        //  viewAttendees.setEvent(ep);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(mainProfile, "Profile Details");
        viewPagerAdapter.addFragments(eventsAttended, "Events Attended");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE, getResources().getColor(R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabLayout.getTabAt(0).setText("Profile Details");
        tabLayout.getTabAt(1).setText("Events Attended");


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.profile_floating_action_menu);
       /* editprofile = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.editprofile);
        editprofile.setColorRipple(Color.parseColor("#66efecec"));
        editprofile.setColorNormalResId(R.color.colorPrimaryDark);
        editprofile.setColorPressedResId(R.color.colorPrimary);*/
        logout = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.logout);
        logout.setColorRipple(Color.parseColor("#66efecec"));
        logout.setColorPressedResId(R.color.colorPrimary);
        logout.setColorNormalResId(R.color.colorPrimaryDark);


        /*TextView*/


        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //delete ticket
                ContextThemeWrapper ctw = new ContextThemeWrapper(Profile.this, R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // edit text
                                        Toast.makeText(Profile.this, "Logged out!", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Profile.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
       /* editprofile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                startActivity(i);

            }
        });*/






    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, NavDrawer.class);
        startActivity(i);
        finish();
    }


}
