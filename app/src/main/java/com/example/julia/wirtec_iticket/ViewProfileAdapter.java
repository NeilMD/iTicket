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

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewProfileAdapter extends RecyclerView.Adapter<ViewProfileAdapter.ViewProfileViewHolder>{
    private ArrayList<String> data;
    ViewGroup parent;

    public ViewProfileAdapter(ArrayList <String> data){
        this.data = data;
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
        String curr = data.get(position);
        //set image
        holder.event.setText(curr);
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(curr);
        holder.datetime.setText(curr);


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