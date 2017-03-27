package com.example.julia.wirtec_iticket;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    java.util.Calendar newDate;
    int dateyear, month, day, hour, timeminute;
    boolean timecorrect = true, datecorrect = true;
    boolean dateset;
    SimpleDateFormat simpleDateFormat;
    Activity thisactivity;



    EditText datetime, event, place, about, ticketlimit, price;
    CheckBox yes, no;
    RadioButton one, two, three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Add Event");

        datetime = (EditText) findViewById(R.id.ad_datetime_value);
        event = (EditText) findViewById(R.id.ad_name_value);
        place = (EditText) findViewById(R.id.ad_place_value);
        about = (EditText) findViewById(R.id.ad_about_value);
        ticketlimit = (EditText) findViewById(R.id.ad_numtickets_value);
        price = (EditText) findViewById(R.id.ad_price_value);
        yes = (CheckBox) findViewById(R.id.yes);
        no = (CheckBox) findViewById(R.id.no);

        simpleDateFormat = new SimpleDateFormat("EEE, MMMMMMMM dd, yyyy ' at ' HH:mm", Locale.ENGLISH);
        datetime = (EditText) findViewById(R.id.ad_datetime_value);

        datetime.setInputType(InputType.TYPE_NULL);
        datetime.requestFocus();
        thisactivity = this;



        setDateTimeField();

        //set Background to uploaded background: collapsingToolbarLayout.setBackground();
       final net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout collapsingToolbarLayout
                = (net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
       collapsingToolbarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("IM IN");
                final Dialog addpic = new Dialog(thisactivity);
                addpic.setContentView(R.layout.addeventpic_dialog);
                addpic.setCancelable(true);
                addpic.setCanceledOnTouchOutside(true);
                addpic.setTitle("Choose Event Picture");

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

                Button accept =(Button) addpic.findViewById(R.id.eaccept);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String value;
                        if (one.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.ticketsbg));
                        } else if (two.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.raffleticket));
                        } else if (three.isChecked()){
                            collapsingToolbarLayout.setBackground(getResources().getDrawable(R.drawable.specialevent));
                        }
                        //save pic to event here
                        addpic.dismiss();
                    }
                });
            }
        });

        yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    no.setChecked(false);
                }
            }
        });
        no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    yes.setChecked(false);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.confirmaddevent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset errors.
                datetime.setError(null);
                event.setError(null);
                place.setError(null);
                about.setError(null);
                ticketlimit.setError(null);
                price.setError(null);
                boolean cancel = false;

                // Store values at the time of the login attempt.
                String ename = event.getText().toString().trim();
                String eplace = place.getText().toString().trim();
                String eprice = price.getText().toString().trim();
                String elimit = ticketlimit.getText().toString().trim();
                String edatetime = datetime.getText().toString().trim();
                String eabout = about.getText().toString().trim();

                if (TextUtils.isEmpty(eabout)) {
                    about.setError(getString(R.string.error_field_required));
                    cancel = true;
                }

                if (TextUtils.isEmpty(edatetime)) {
                    datetime.setError(getString(R.string.error_field_required));
                    cancel = true;
                }

                if (TextUtils.isEmpty(eplace)) {
                    place.setError(getString(R.string.error_field_required));
                    cancel = true;
                }

                boolean parsable = true;
                try{
                    Double.parseDouble(eprice);
                }catch(NumberFormatException e){
                    parsable = false;
                }

                if (TextUtils.isEmpty(price.getText().toString().trim())) {
                    price.setError(getString(R.string.error_field_required));
                    cancel = true;
                } else if (parsable == false) {
                    price.setError("Invalid price value");
                    cancel = true;
                }else if (Double.parseDouble(eprice) < 0) {
                    price.setError("Price cannot be negative");
                    cancel = true;
                }

                parsable = true;
                try{
                    Integer.parseInt(elimit);
                }catch(NumberFormatException e){
                    parsable = false;
                }

                if (TextUtils.isEmpty(ticketlimit.getText().toString().trim())) {
                    ticketlimit.setError(getString(R.string.error_field_required));
                    cancel = true;
                }  else if (parsable == false) {
                    ticketlimit.setError("Invalid ticket limit value");
                    cancel = true;
                }else if (Integer.parseInt(elimit) <= 0) {
                    ticketlimit.setError("Ticket limit cannot be less than or equal to 0");
                    cancel = true;
                }

                if (TextUtils.isEmpty(ename)) {
                    event.setError(getString(R.string.error_field_required));
                    cancel = true;
                } else if (ename.length() > 50) {
                    event.setError("Event name should be 50 characters or less");
                    cancel = true;
                }


                if (!cancel) {
                    ContextThemeWrapper ctw = new ContextThemeWrapper(AddEvent.this, R.style.AlertDialogCustom);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Are you sure you want to add this event?")
                            .setCancelable(true)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // get user input and set it to result
                                            // edit text
                                            Toast.makeText(AddEvent.this, "Event Created! Request Code Available in Event Details!", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(AddEvent.this, NavDrawer.class);
                                            startActivity(i);
                                            finish();

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

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(AddEvent.this, NavDrawer.class);
        startActivity(i);
        finish();
    }

    private void setDateTimeField() {
        datetime.setOnClickListener(this);
        final Calendar newCalendar = Calendar.getInstance();



        do{
            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate = java.util.Calendar.getInstance();
                    dateyear = year;
                    month = monthOfYear;
                    day = dayOfMonth;
                    if(newCalendar.get(Calendar.YEAR) > dateyear || newCalendar.get(java.util.Calendar.YEAR) == dateyear && newCalendar.get(java.util.Calendar.MONTH) > month || newCalendar.get(java.util.Calendar.YEAR) == dateyear && newCalendar.get(java.util.Calendar.MONTH) == month &&  newCalendar.get(java.util.Calendar.DAY_OF_MONTH) > day){
                        datecorrect = false;
                        Toast.makeText(getBaseContext(), "Date is invalid! Please enter valid date.", Toast.LENGTH_LONG).show();
                    } else {
                        datecorrect = true;
                    }

                    if (datecorrect){
                        timePickerDialog.show();
                    }
                }

            },newCalendar.get(java.util.Calendar.YEAR), newCalendar.get(java.util.Calendar.MONTH), newCalendar.get(java.util.Calendar.DAY_OF_MONTH));
        } while (!datecorrect);




        do {
            timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    newDate = java.util.Calendar.getInstance();
                    hour = hourOfDay;
                    timeminute = minute;

                    if(newCalendar.get(java.util.Calendar.YEAR) == dateyear && newCalendar.get(java.util.Calendar.MONTH) == month &&  newCalendar.get(java.util.Calendar.DAY_OF_MONTH) == day && newCalendar.get(java.util.Calendar.HOUR_OF_DAY) > hour || newCalendar.get(java.util.Calendar.YEAR) == dateyear && newCalendar.get(java.util.Calendar.MONTH) == month && newCalendar.get(java.util.Calendar.DAY_OF_MONTH) == day && newCalendar.get(java.util.Calendar.HOUR_OF_DAY) == hour && newCalendar.get(java.util.Calendar.MINUTE) > timeminute){
                        datecorrect = false;
                        Toast.makeText(getBaseContext(), "Time is invalid! Please enter valid time.", Toast.LENGTH_LONG).show();
                    } else {
                        datecorrect = true;
                    }
                    if (timecorrect){
                        newDate.set(dateyear, month, day, hour, timeminute);
                        datetime.setText(simpleDateFormat.format(newDate.getTime()));
                    }

                }
            }, newCalendar.get(java.util.Calendar.HOUR_OF_DAY), newCalendar.get(java.util.Calendar.MINUTE), true);
        }while (!timecorrect );

    }

    @Override
    public void onClick(View v) {
        if (v == datetime){
            datePickerDialog.show();
        }
    }


}
