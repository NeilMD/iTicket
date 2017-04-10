package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.security.SecureRandom;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewProfileAdapter extends RecyclerView.Adapter<ViewProfileAdapter.ViewProfileViewHolder>{

    ViewGroup parent;
    private ArrayList<String> data;
    private DatabaseReference ref;
    private LayoutInflater mInflater;
    private ChildEventListener cel;

    private ArrayList<String> eid = new ArrayList<>();
    private ArrayList<EventParcelable> e = new ArrayList<>();

    public ViewProfileAdapter(ArrayList <String> data, DatabaseReference ref)
    {
        this.data = data;
        this.ref = ref;

        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EventParcelable eve = dataSnapshot.getValue(EventParcelable.class);

                eid.add(dataSnapshot.getKey());
                e.add(eve);

                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                EventParcelable ne = dataSnapshot.getValue(EventParcelable.class);
                String neid = dataSnapshot.getKey();

                int idx = eid.indexOf(neid);
                if(idx > -1 ){
                    e.set(idx,ne);
                    notifyItemChanged(idx);
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String eke = dataSnapshot.getKey();
                int index = eid.indexOf(eke);
                if(index > -1){
                    eid.remove(index);
                    e.remove(index);
                    notifyItemRemoved(index);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.addChildEventListener(cel2);
        cel =cel2;

    }

    @Override
    //inflate XML (ticket)
    public ViewProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_event, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewProfileViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewProfileViewHolder holder, int position) {
        EventParcelable ep = e.get(position);
//        String curr = data.get(position);
        //set image
        holder.event.setText(ep.getEventname());
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(ep.getPlace());
        Date n = new Date(ep.getDate());
        Time n2 = new Time(ep.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        holder.datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));


    }


    @Override
    //
    public int getItemCount() {
        return data.size();
    }

    public class ViewProfileViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView eventimage;
        TextView event;
        TextView place;
        TextView datetime;

        public ViewProfileViewHolder(View itemView){
            super(itemView);

            eventimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.requestimage);
            event = (TextView) itemView.findViewById(R.id.eventname);
            place = (TextView) itemView.findViewById(R.id.eventplace);
            datetime = (TextView) itemView.findViewById(R.id.eventdatetime);

        }



    }
}