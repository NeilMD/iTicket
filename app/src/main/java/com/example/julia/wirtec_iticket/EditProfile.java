package com.example.julia.wirtec_iticket;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    EditText name, contactnum;
    Activity thisactivity;
    RadioButton one, two, three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        thisactivity = this;

        name = (EditText) findViewById(R.id.pe_name_value);
        contactnum = (EditText) findViewById(R.id.pd_contactnum_value);

        //set Background to uploaded background: collapsingToolbarLayout.setBackground();
        final net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout collapsingToolbarLayout
                = (net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("IM IN");
                final Dialog addpic = new Dialog(thisactivity);
                addpic.setContentView(R.layout.addprofilepic_dialog);
                addpic.setCancelable(true);
                addpic.setCanceledOnTouchOutside(true);
                addpic.setTitle("Choose Profile Picture");
                addpic.show();

                one = (RadioButton) addpic.findViewById(R.id.one);
                two = (RadioButton) addpic.findViewById(R.id.two);
                three = (RadioButton) addpic.findViewById(R.id.three);

                one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                        {
                            two.setChecked(false);
                            three.setChecked(false);
                        }
                    }
                });
                two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                        {
                            three.setChecked(false);
                            one.setChecked(false);
                        }
                    }
                });
                three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                        {
                            two.setChecked(false);
                            one.setChecked(false);
                        }
                    }
                });

                Button accept =(Button) addpic.findViewById(R.id.paccept);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (one.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.john));
                        } else if (two.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.hat));
                        } else if (three.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.wall));
                        }
                        //save pic to profile here
                        addpic.dismiss();
                    }
                });


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.confirmeditprofile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Reset errors.
                    name.setError(null);
                    contactnum.setError(null);
                    boolean cancel = false;

                    // Store values at the time of the login attempt.
                    String ename = name.getText().toString().trim();
                    String econtact = contactnum.getText().toString().trim();


                    // Check if re-entered password is same as first password
                    if (TextUtils.isEmpty(econtact)) {
                        contactnum.setError(getString(R.string.error_field_required));
                        cancel = true;
                    }

                    if (TextUtils.isEmpty(ename)) {
                        name.setError(getString(R.string.error_field_required));
                        cancel = true;
                    } else if (ename.length() > 41) {
                        name.setError("Name should be 40 letters or less");
                        cancel = true;
                    }

                    if (!cancel) {
                        ContextThemeWrapper ctw = new ContextThemeWrapper(EditProfile.this, R.style.AlertDialogCustom);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Are you sure you want to save these changes?")
                                .setCancelable(true)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                Toast.makeText(EditProfile.this, "Profile Edited!", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(EditProfile.this, NavDrawer.class);
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



            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(EditProfile.this, Profile.class);
        startActivity(i);
        finish();
    }



}
