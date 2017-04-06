package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewRequestsAdapter extends TicketAdapter<ViewRequestsAdapter.ViewRequestsViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    private DatabaseReference ref;
    private ChildEventListener cel;

    private ArrayList<Request> e = new ArrayList<>();
    private ArrayList<String> eid = new ArrayList<>();

    private ArrayList<String> uid = new ArrayList<>();
    ViewGroup parent;
    DataSnapshot ds;
    String us;
    int key;
    public ViewRequestsAdapter(final ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;



        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ds = dataSnapshot;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    for(DataSnapshot snapshot2:snapshot.getChildren()){
                        Request eve = snapshot.getValue(Request.class);


                        eid.add(dataSnapshot.getKey());
                        e.add(eve);
                        add(snapshot.getKey());
                        uid.add(eve.getUid());
                        int ccc = eid.indexOf(dataSnapshot.getKey());
                    key = ccc;
                    us = snapshot.getKey();
                    notifyItemInserted(ccc);
//                data.add(dataSnapshot.getKey());
//                        notifyItemInserted(e.size() - 1);
//                    }
                }
//                String eventCode = dataSnapshot.getKey();
//                String userUid = dataSnapshot.child(eventCode).getKey();
//                key = eventCode;
//                us = userUid;
//                Request eve = dataSnapshot.getValue(Request.class);
//                ds = dataSnapshot;
//                eid.add(dataSnapshot.getKey());
//                e.add(eve);
////                data.add(dataSnapshot.getKey());
//                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Request rTemp;
                int ctr=0;


//
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Request eve = snapshot.getValue(Request.class);
                    ds = snapshot;
                    int first = eid.indexOf(dataSnapshot.getKey());
                    int last = eid.lastIndexOf(dataSnapshot.getKey());
                    int key = 0;
                    for(int x = first; x < last + 1; x++){
                        if(uid.get(x).equals(eve.getUid())){
                            key = x;
                        }
                    }

//                    e.set(key,eve);
//                    eid.set(key,dataSnapshot.getKey());


//                        eid.add(dataSnapshot.getKey());
//                        add(snapshot.getKey());
//                        e.add(eve);
//                    if (!eve.equals(e.get(ctr))) {
//                        e.set(ctr + 1, eve);
////                        eid.set(ctr,dataSnapshot.getKey());
//                        notifyItemChanged(ctr + 1);
//
//                    }
//                    ctr++;
                }notify();
////                    for(DataSnapshot snapshot2:snapshot.getChildren()){
//                        Request eve = snapshot.getValue(Request.class);
//                        ds = snapshot;
//
//                        eid.add(dataSnapshot.getKey());
//                    add(snapshot.getKey());
//                        e.add(eve);
//
//                        notifyItemInserted(e.size() - 1);
////                    }

//                Request ne = dataSnapshot.getValue(Request.class);
//                String neid = dataSnapshot.getKey();
//
//                int idx = eid.indexOf(neid);
//                if(idx > -1 ){
//                    e.set(idx,ne);
//                    notifyItemChanged(idx);
//                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int ctr = 0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

////                    for(DataSnapshot snapshot2:dataSnapshot.getChildren()){
                    Request eve = snapshot.getValue(Request.class);
                    ds = snapshot;
                    String ccc = dataSnapshot.getKey();

//                    int c3 = eid.indexOf(ccc);
//                    int c4 = uid.indexOf(eve.getUid());
//                    uid.remove(c4);
//                    eid.remove(c4);
//                    e.remove(c4);
//                    notifyItemRemoved(c4);

//                        eid.add(dataSnapshot.getKey());
//                    add(snapshot.getKey());
//                        e.add(eve);
//
//                        if(!eve.equals(e.get(ctr))){
//                            e.remove(ctr-1);
//                            eid.remove(ctr-1);
//                            notifyItemRemoved(ctr);
//                        }
//                    ctr++;
                }
//                String eke = dataSnapshot.getKey();
//                int index = eid.indexOf(eke);
//                if(index > -1){
//                    eid.remove(index);
//                    e.remove(index);
//                    notifyItemRemoved(index);
//                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

//        this.data = eid;
        ref.addChildEventListener(cel2);
//        addAll(eid);
        cel = cel2;
//        this.data = eid;
    }

    @Override
    //inflate XML (ticket)
    public ViewRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewRequestsViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewRequestsViewHolder holder,final int position) {
//        String curr = data.get(position);
        Request req = e.get(position);
        //set image
        
        holder.name.setText(ds+" >>>>>>"+us+">>>>>>>>>>>>>>>"+uid);
        holder.name.setTypeface(null, Typeface.BOLD);
        /*holder.event.setText(req.getEvent().toString());*/
        holder.email.setText(req.getEvent());
        holder.numtickets.setText(req.getNumberOfTicketRequested());


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.reject.setTag(req);


        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View v2 = v;
                Request req = (Request) v.getTag();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(eid.get(position)).child(req.getUid());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(eid.get(position));
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(v2.getContext(),"Success!",Toast.LENGTH_LONG).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(v2.getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Toast.makeText(v2.getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v2.getContext(),"Failed!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(v2.getContext(),"Failed!",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
    public ArrayList<String> geteid(){
        return eid;
    }

    @Override
    public long getHeaderId(int position) {
        return eid.get(position).hashCode();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_test, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        Request req = e.get(position);
        textView.setText(req.getEvent());
//        holder.itemView.setBackgroundColor(parent.getResources().getColor(R.color.headers)); //nagerror
    }

//    private int getRandomColor() {
//        SecureRandom rgen = new SecureRandom();
//        return Color.HSVToColor(150, new float[]{
//                rgen.nextInt(359), 1, 1
//        });
//    }


    @Override
    //
    public int getItemCount() {
        return e.size();
    }

    public class ViewRequestsViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView requestimage;
        /*TextView event;*/
        TextView name;
        TextView numtickets;
        TextView email;
        Button accept;
        Button reject;

        public ViewRequestsViewHolder(View itemView){
            super(itemView);

            requestimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.requestimage);
            /*event = (TextView) itemView.findViewById(R.id.requestevent);*/
            name = (TextView) itemView.findViewById(R.id.name);
            numtickets = (TextView) itemView.findViewById(R.id.numtickets);
            email = (TextView) itemView.findViewById(R.id.email);
            accept = (Button) itemView.findViewById(R.id.accept);
            reject = (Button) itemView.findViewById(R.id.reject);
        }



    }
}