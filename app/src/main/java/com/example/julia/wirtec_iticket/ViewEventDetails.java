package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import layout.EventDetails;
import layout.ViewAttendees;

public class ViewEventDetails extends AppCompatActivity {
    TextView event;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ViewAttendees viewAttendees;
    EventDetails eventDetails;
    public FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton editEvent, deleteEvent, checkEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EventParcelable ep = getIntent().getParcelableExtra("event");

        eventDetails = new EventDetails();
        eventDetails.setEvent(ep);
        setTitle(ep.getEventname());
        viewAttendees = new ViewAttendees();
        viewAttendees.setEvent(ep);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(eventDetails, "Event Details");

        viewPagerAdapter.addFragments(viewAttendees, "View Attendees");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE, getResources().getColor(R.color.colorAccent));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabLayout.getTabAt(0).setText("Event Details");
        tabLayout.getTabAt(1).setText("View Attendees");

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.event_floating_action_menu);
        editEvent = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.editevent);
        editEvent.setColorRipple(Color.parseColor("#66efecec"));
        editEvent.setColorNormalResId(R.color.colorPrimaryDark);
        editEvent.setColorPressedResId(R.color.colorPrimary);
        deleteEvent = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.deleteevent);
        deleteEvent.setColorRipple(Color.parseColor("#66efecec"));
        deleteEvent.setColorPressedResId(R.color.colorPrimary);
        deleteEvent.setColorNormalResId(R.color.colorPrimaryDark);
        checkEvent = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.checktickets);
        checkEvent.setColorRipple(Color.parseColor("#66efecec"));
        checkEvent.setColorPressedResId(R.color.colorPrimary);
        checkEvent.setColorNormalResId(R.color.colorPrimaryDark);


        checkEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(ViewEventDetails.this, R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("You are now entering Check Event Mode. This requires your NFC to be enabled. Proceed?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // edit text
                                        Intent i = new Intent(ViewEventDetails.this, FullscreenEventCheck.class);
                                        startActivity(i);
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //delete ticket
                ContextThemeWrapper ctw = new ContextThemeWrapper(ViewEventDetails.this, R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this event?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // edit text
                                        Toast.makeText(ViewEventDetails.this, "Event Deleted!", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(ViewEventDetails.this, NavDrawer.class);
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
        editEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent( ViewEventDetails.this, EditEventDetails.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ViewEventDetails.this, NavDrawer.class);
        startActivity(i);
        finish();
    }
}
