package com.example.a844302049.carvideo1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a844302049.carvideo1.R;

/**
 * Created by 844302049 on 2018/7/18.
 */
public class Fragment7 extends Fragment {
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup con, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_layout1,con,false);
        return mView;
    }
}