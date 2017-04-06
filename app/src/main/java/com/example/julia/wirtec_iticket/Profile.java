package com.example.julia.wirtec_iticket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    ViewProfileAdapter viewProfileAdapter;
    ArrayList<String> data;
    RecyclerView rvProfile;
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




        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.profile_floating_action_menu);
        editprofile = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.editprofile);
        editprofile.setColorRipple(Color.parseColor("#66efecec"));
        editprofile.setColorNormalResId(R.color.colorPrimaryDark);
        editprofile.setColorPressedResId(R.color.colorPrimary);
        logout = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.logout);
        logout.setColorRipple(Color.parseColor("#66efecec"));
        logout.setColorPressedResId(R.color.colorPrimary);
        logout.setColorNormalResId(R.color.colorPrimaryDark);


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
        editprofile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                startActivity(i);

            }
        });


      rvProfile = (RecyclerView) findViewById(R.id.rv_eventstickets);

        data = new ArrayList<String>();
        data.add("Alpha");
        data.add("Beta");
        data.add("Charlie");
        data.add("Delta");
        data.add("Echo");
        data.add("Foxtrot");
        data.add("Gamma");
        data.add("Hotel");
        data.add("India");
        data.add("Juliet");
        data.add("Kilo");
        data.add("Lima");
        data.add("Mama");

        if(rvProfile != null) {
            viewProfileAdapter = new ViewProfileAdapter(data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
            rvProfile.setLayoutManager(layoutManager);
            /*DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvProfile.getContext());
            rvProfile.addItemDecoration(dividerItemDecoration);*/
            rvProfile.setAdapter(viewProfileAdapter);

            final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvProfile.getLayoutManager();
           /** rvProfile.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                        materialDesignFAM.setVisibility(View.INVISIBLE);
                    } else {
                        materialDesignFAM.setVisibility(View.VISIBLE);
                    }
                }

            });**/

        }


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, NavDrawer.class);
        startActivity(i);
        finish();
    }


}
