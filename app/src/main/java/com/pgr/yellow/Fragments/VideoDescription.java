package com.pgr.yellow.Fragments;

/**
 * Created by BMS0020 on 6/11/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgr.yellow.R;

public class VideoDescription extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.oraganization, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //TextView tv=(TextView)getActivity().findViewById(R.id.textset);
        //tv.setText("Video Description");
    }

}