package com.pgr.yellow.Fragments;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgr.yellow.Activities.BaseContainerFragment;
import com.pgr.yellow.R;

/**
 * Created by BMS0020 on 6/11/2015.
 */
public class Tab1Container  extends BaseContainerFragment {
    private boolean IsViewInited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("Ritesh", "Tab1");
        return inflater.inflate(R.layout.container_framelayout, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!IsViewInited) {
            IsViewInited = true;
            initView();
        }
    }

    private void initView() {
        replaceFragment(new CategoryFragment(), false);
    }

}
