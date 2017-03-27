package com.example.julia.wirtec_iticket;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import layout.ViewAttendees;

/**
 * Created by Julia on 3/11/2017.
 */

public class ViewAttendeesAdapter extends RecyclerView.Adapter<ViewAttendeesAdapter.ViewAttendeesViewHolder>{

    private ArrayList<String> data;

    public ViewAttendeesAdapter(ArrayList <String> data){
        this.data = data;
    }

    @Override
    //inflate XML (ticket)
    public ViewAttendeesAdapter.ViewAttendeesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendees, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewAttendeesAdapter.ViewAttendeesViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewAttendeesAdapter.ViewAttendeesViewHolder holder, int position) {
        String curr = data.get(position);
        //set image here
        holder.name.setText(curr);
        holder.name.setTypeface(null, Typeface.BOLD);
        holder.email.setText(curr);
        holder.numTickets.setText(curr);

    }

    @Override
    //
    public int getItemCount() {
        return data.size();
    }

    public class ViewAttendeesViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView image;
        TextView name;
        TextView email;
        TextView numTickets;


        public ViewAttendeesViewHolder (View itemView){
            super(itemView);
            image = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.attendeesimage);
            name = (TextView) itemView.findViewById(R.id.attendeesname);
            email = (TextView) itemView.findViewById(R.id.attendeesemail);
            numTickets = (TextView) itemView.findViewById(R.id.attnumtickets);

        }


    }
}

