package com.pgr.yellow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RAHUPATHI on 6/12/2015.
 */
public class OranizationDetailList extends Fragment {

    View vDetail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        vDetail = (View)inflater.inflate(R.layout.organizationdetail, container, false);
        return vDetail;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        LoadDetailList();
    }
    private void LoadDetailList(){



    }
}
