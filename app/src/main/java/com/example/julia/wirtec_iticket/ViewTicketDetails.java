package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import layout.TicketDetails;

public class ViewTicketDetails extends AppCompatActivity {

    com.github.clans.fab.FloatingActionButton deleteTicket, attendEvent;
    FloatingActionMenu materialDesignFAM;

    TicketDetails ticketDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TicketParcelable tp = getIntent().getParcelableExtra("ticket");

        ticketDetails = new TicketDetails();
        ticketDetails.setTicket(tp);
        setTitle(tp.getEventname());
        TextView place = (TextView) findViewById(R.id.td_place_value);
        TextView datetime = (TextView) findViewById(R.id.td_datetime_value);
        TextView about = (TextView) findViewById(R.id.td_about_value);
        place.setText(tp.getPlace());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        Date n = new Date(tp.getDate());
        Time n2 = new Time(tp.getTime());
        datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));
        about.setText(tp.getEventdesc());

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.ticket_floating_action_menu);
        deleteTicket = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.deleteticket);
        attendEvent = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.useticket);
        deleteTicket.setColorRipple(Color.parseColor("#66efecec"));
        deleteTicket.setColorNormalResId(R.color.colorPrimaryDark);
        deleteTicket.setColorPressedResId(R.color.colorPrimary);
        attendEvent.setColorRipple(Color.parseColor("#66efecec"));
        attendEvent.setColorNormalResId(R.color.colorPrimaryDark);
        attendEvent.setColorPressedResId(R.color.colorPrimary);

        attendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextThemeWrapper ctw = new ContextThemeWrapper(ViewTicketDetails.this, R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("You are now entering Attend Event Mode. This requires your NFC to be enabled. Proceed?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // edit text
                                        Intent i = new Intent(ViewTicketDetails.this, FullscreenTicketCheck.class);
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

                deleteTicket.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //delete ticket
                        ContextThemeWrapper ctw = new ContextThemeWrapper(ViewTicketDetails.this, R.style.AlertDialogCustom);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Are you sure you want to delete this ticket?")
                                .setCancelable(true)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // edit text
                                                Toast.makeText(ViewTicketDetails.this, "Ticket Deleted!", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(ViewTicketDetails.this, NavDrawer.class);
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
                });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ViewTicketDetails.this, NavDrawer.class);
        startActivity(i);
        finish();
    }
}
