package com.example.julia.wirtec_iticket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.julia.wirtec_iticket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainProfile extends Fragment {


    public MainProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_profile, container, false);
    }

}
