package com.example.julia.wirtec_iticket;

/**
 * Created by Julia on 2/21/2017.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

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
 * Created by Julia on 1/26/2017.
 */

public class ViewTicketsAdapter extends RecyclerView.Adapter<ViewTicketsAdapter.ViewTicketsViewHolder>{

    private ArrayList<String> data;
    private DatabaseReference ref;
    private LayoutInflater mInflater;
    private ChildEventListener cel;

    private ArrayList<String> eid = new ArrayList<>();
    private ArrayList<Ticket> e = new ArrayList<>();

    public ViewTicketsAdapter(ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;
        this.data = data;

        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Ticket eve = dataSnapshot.getValue(Ticket.class);

                eid.add(dataSnapshot.getKey());
                e.add(eve);

                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Ticket ne = dataSnapshot.getValue(Ticket.class);
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
    public ViewTicketsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewTicketsAdapter.ViewTicketsViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return e.size();
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewTicketsViewHolder holder, int position) {
        Ticket currE = e.get(position);

        String curr = data.get(position);
        //set image here
        holder.event.setText(currE.getEventname());
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(currE.getPlace());
        Date n = new Date(currE.getDate());
        Time n2 = new Time(currE.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        holder.datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));
        holder.numtickets.setText(currE.getEventname() + "");
        /*holder.status.setText("Sta");
        holder.status.setTypeface(null, Typeface.BOLD);*/
        holder.con.setTag(position);
        holder.con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    int position = (int) v.getTag();
                    mOnItemClickListener.onItemClick(e.get(position),eid.get(position));
                }
            }
        });
        holder.con.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnLongItemClickListener != null){
                    int position = (int) v.getTag();
                    mOnLongItemClickListener.onLongItemClick(e.get(position),eid.get(position));
                }
                return false;
            }
        });

        /**set text background accdg to status
         <GREY = UNUSED>
         <GREEN = USED>
         <YELLOW = PENDING>
         <RED = EXIT>**/
        /*holder.status.setBackgroundColor(Color.WHITE);*/
        if(currE.getStatus().equalsIgnoreCase("unused")) {
            holder.status.setImageResource(R.drawable.logo_unused);
        }
        else if(currE.getStatus().equalsIgnoreCase("pending")) {
            holder.status.setImageResource(R.drawable.logo_pending);
        }
        else if(currE.getStatus().equalsIgnoreCase("used")) {
            holder.status.setImageResource(R.drawable.logo_used);
        }
        else if(currE.getStatus().equalsIgnoreCase("exit")) {
            holder.status.setImageResource(R.drawable.logo_exit);
        }
        /*holder.status.setTextColor(Color.BLACK);*/

    }


    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(Ticket ticket, String ticketId);
    }

    private OnLongItemClickListener mOnLongItemClickListener;

    public void setmOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener){
        mOnLongItemClickListener = onLongItemClickListener;
    }

    public interface OnLongItemClickListener{
        public void onLongItemClick(Ticket ticket, String ticketId);
    }

    public class ViewTicketsViewHolder extends RecyclerView.ViewHolder {
        dev.dworks.libs.astickyheader.ui.SquareImageView image;
        TextView event;
        TextView place;
        TextView datetime;
        TextView numtickets;
        ImageView status;
        ImageView status_v2;
        View con;

        public ViewTicketsViewHolder(View itemView) {
            super(itemView);
            image = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.ticketimage);
            event = (TextView) itemView.findViewById(R.id.event);
            place = (TextView) itemView.findViewById(R.id.place);
            datetime = (TextView) itemView.findViewById(R.id.datetime);
            numtickets = (TextView) itemView.findViewById(R.id.numtickets);
            status = (ImageView) itemView.findViewById(R.id.status);
            /*status_v2 = (ImageView) itemView.findViewById(R.id.status_v2);*/
            con = itemView.findViewById(R.id.ticket);
        }


    }



}

