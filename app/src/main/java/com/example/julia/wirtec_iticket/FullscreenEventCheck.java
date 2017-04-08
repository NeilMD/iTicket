package com.example.julia.wirtec_iticket;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
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

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenEventCheck extends AppCompatActivity {
    Button dummy;
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    EventParcelable ev;
    private static final String _MIME_TYPE = "text/plain";
    NfcAdapter nfc;
    IntentFilter[] intentFilters;
    PendingIntent pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen_event_check);
        ev  = getIntent().getParcelableExtra("event");
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(1000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        mContentView.startAnimation(animation);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.clearAnimation();
            }
        });
        dummy = (Button) findViewById(R.id.dummy_button);
        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ContextThemeWrapper ctw = new ContextThemeWrapper(FullscreenEventCheck.this, R.style.AlertDialogCustom);
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
//
//                // set dialog message
//                alertDialogBuilder
//                        .setMessage("You are about to exit Check Event Mode. Proceed?")
//                        .setCancelable(true)
//                        .setPositiveButton("Yes",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        // edit text
////                                        Intent i = new Intent(FullscreenEventCheck.this, NavDrawer.class);
////                                        startActivity(i);
//                                        finish();
//                                    }
//                                })
//                        .setNegativeButton("No",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                // create alert dialog
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // show it
//                alertDialog.show();
                Intent i = new Intent(FullscreenEventCheck.this, NavDrawer.class);
                startActivity(i);
               finish();
            }
        });
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        // NFC Checking
        PackageManager pm = this.getPackageManager();
        // Check if NFC is available
        if(!pm.hasSystemFeature(PackageManager.FEATURE_NFC)){
            Toast.makeText(this, "The device does not have NFC hardware.",
                    Toast.LENGTH_LONG).show();
        }
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
            // Android Beam is not supported
            Toast.makeText(this, "Android Beam is not supported.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            nfc = NfcAdapter.getDefaultAdapter(this);
            if(!nfc.isEnabled()){
                // NFC is disabled, show the settings UI
                // to enable NFC
                Toast.makeText(getBaseContext(),"Please enable NFC", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));

            }
            pi = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            // IntentFilter to be scanned
            IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            try
            {
                ndefDetected.addDataType(_MIME_TYPE);
            } catch (IntentFilter.MalformedMimeTypeException e)
            {

            }
            intentFilters = new IntentFilter[] { ndefDetected };
            // Register callback to set NDEF message
//            nfc.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
//            nfc.setOnNdefPushCompleteCallback(this, this);
        }




    }
    @Override
    protected void onResume() {
        super.onResume();
        enableNdefExchangeMode();
    }

    private void enableNdefExchangeMode() {
//        NdefRecord nfcr = NdefRecord.createTextRecord("en",temp);
//        //Send ndefmessage
//        nfc.setNdefPushMessage(
//                new NdefMessage(nfcr),this );

        //Listen to the intentfilter created on the oncreate method
        nfc.enableForegroundDispatch(this, pi,
                intentFilters, null);
        //finish();
    }

    // Fires when the listener of intentfilter has found the same intent filter
    @Override
    protected void onNewIntent(Intent intent) {
        //NDEF exchange mode
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {

            NdefMessage[] msgs = getNdefMessages(intent);
            // SEE message. SUCCESS!
//            Toast.makeText(this, msgs.toString(), Toast.LENGTH_LONG).show();
        }
    }
    NdefMessage[] getNdefMessages(Intent intent) {
        // Parse the intent
        NdefMessage[] msgs = null;

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs =

                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
//                for (int i = 0; i < rawMsgs.length; i++) {
//                    msgs[i] = (NdefMessage) rawMsgs[i];
//                    Toast.makeText(getBaseContext(),msgs[i].getRecords()[0].getPayload().toString(),Toast.LENGTH_LONG).show();
//                }
                // It will get the text+message(see ndefRecord.createtext up)
                NdefMessage msg = (NdefMessage) rawMsgs[0];
                byte[] n = msg.getRecords()[0].getPayload();
                String m = new String(n);
                if(m != null){
                    String rm = m.substring(3);
                    final String code = rm.substring(0,5);
                    final String auth = rm.substring(5);
                    Log.i("Send Dta:",code+"    AuthID:  "+auth+"  RM:"+rm);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(auth).child(code);

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("status").getValue().equals("unused")){
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(auth).child(code);
                                ref.child("status").setValue("used").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(auth);
                                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                final Account acc = dataSnapshot.getValue(Account.class);
                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-attendees");
                                                ref.child(ev.getCode()).child(acc.getUid()).setValue(acc).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-attended").child(auth);
                                                        ref.child(ev.getCode()).setValue(ev).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(getBaseContext(),acc.getName()+ "has attended"+ ev.getEventname()+"!",Toast.LENGTH_LONG).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                            }
                                                        });

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                            }else if(dataSnapshot.child("status").getValue().equals("used")){
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(auth).child(code);
                                ref.child("status").setValue("exit").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getBaseContext(),"Success Entry!",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                            }else{
                                Toast.makeText(getBaseContext(),"Failed!",Toast.LENGTH_LONG).show();

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(getBaseContext(),"Code:"+code+"    Auth:"+auth,Toast.LENGTH_LONG).show();
                }



            } else {
                // Unknown tag type
                byte[] empty = new byte[] {};
                NdefRecord record =
                        new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {
                        record
                });
                msgs = new NdefMessage[] {
                        msg
                };
            }
        } else {

            finish();
        }
        return msgs;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onBackPressed() {
        onResume();

    }
}
