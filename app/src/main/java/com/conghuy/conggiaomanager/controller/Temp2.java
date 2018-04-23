package com.conghuy.conggiaomanager.controller;

import android.app.Fragment;
import android.content.Intent;

import com.conghuy.conggiaomanager.MainActivity;

public class Temp2 extends Fragment {
    void abc(){
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
