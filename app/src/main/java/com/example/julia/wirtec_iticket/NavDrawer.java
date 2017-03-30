package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class NavDrawer extends AppCompatActivity {

    //NavigationView navigationView = null;
    Toolbar toolbar = null;
    TabLayout tabLayout ;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ViewEvents viewEvents;
    ViewTickets viewTickets;
    ViewRequests viewRequests;
    Profile profile;
    ImageButton profileButton;
    public FloatingActionMenu materialDesignFAM;
    FloatingActionButton addticket, addevent;
    AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nav_drawer);
        setTitle("");

        activity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setDrawingCacheBackgroundColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        viewEvents = new ViewEvents();
        viewRequests = new ViewRequests();
        viewTickets = new ViewTickets();
        profile = new Profile();



        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(viewTickets, "View Tickets");
        viewPagerAdapter.addFragments(viewEvents, "View Events");
        viewPagerAdapter.addFragments(viewRequests, "View Requests");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_ticketwhite);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_eventwhite);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_requestwhite);

        tabLayout.getTabAt(0).setText("My Tickets");
        tabLayout.getTabAt(1).setText("My Events");
        tabLayout.getTabAt(2).setText("My Requests");

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        addticket = (FloatingActionButton) findViewById(R.id.addticket);
        addticket.setColorRipple(Color.parseColor("#66efecec"));
        addticket.setColorNormalResId(R.color.colorPrimaryDark);
        addticket.setColorPressedResId(R.color.colorPrimary);
        addevent = (FloatingActionButton) findViewById(R.id.addevent);
        addevent.setColorRipple(Color.parseColor("#66efecec"));
        addevent.setColorPressedResId(R.color.colorPrimary);
        addevent.setColorNormalResId(R.color.colorPrimaryDark);


        addticket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(getBaseContext());
                View promptsView = li.inflate(R.layout.addticketprompt, null);

                ContextThemeWrapper ctw = new ContextThemeWrapper(NavDrawer.this, R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                final EditText numberInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogNumberInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        Toast.makeText(activity, "Entered code: " + userInput.getText() + "! \n Number of Tickets Reserved: " + numberInput.getText(), Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("Cancel",
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
        addevent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent( NavDrawer.this, AddEvent.class);
                startActivity(i);

            }
        });
        profileButton = (ImageButton) findViewById(R.id.btn_profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NavDrawer.this, Profile.class);
                startActivity(i);
            }
        });

        /**DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
         this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.setDrawerListener(toggle);
         toggle.syncState();**/






        //navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);


    }


    public FloatingActionMenu getMaterialDesignFAM (){
        return this.materialDesignFAM;
    }

    public FloatingActionButton getAddticket(){
        return this.addticket;
    }

    public FloatingActionButton getAddevent(){
        return this.addevent;
    }


    @Override
    public void onBackPressed() {
        onResume();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /**    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            setTitle("iTicket Profile");
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_event) {
            setTitle("Events");
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_ticket) {
            setTitle("Tickets");
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_requests) {
            setTitle("Ticket Requests");
            viewPager.setCurrentItem(3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }**/
}
