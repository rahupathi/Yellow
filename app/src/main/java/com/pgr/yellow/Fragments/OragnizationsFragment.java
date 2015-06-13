package com.pgr.yellow.Fragments;

/**
 * Created by BMS0020 on 6/11/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pgr.yellow.Adapters.OrganizationAdapter;
import com.pgr.yellow.Models.OrganizationModel;
import com.pgr.yellow.R;

import java.util.ArrayList;

public class OragnizationsFragment extends Fragment {

    private ListView listView;
    private ArrayList<OrganizationModel> organizationModelList = null;
    private OrganizationAdapter adapter;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = (View) inflater.inflate(R.layout.oraganization, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        LoadList();
    }

    private void LoadList() {
        organizationModelList = new ArrayList<OrganizationModel>();
        OrganizationModel objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Annemers");
        objOrganizationMode.setCity("5 bedrijven");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Accountants");
        objOrganizationMode.setCity("4 bedrijven");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Constructions");
        objOrganizationMode.setCity("2 bedrijven");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Vehicle Service");
        objOrganizationMode.setCity("3 bedrijven");
        organizationModelList.add(objOrganizationMode);

        listView = (ListView) v.findViewById(R.id.lvFacilitylistView);
        adapter = new OrganizationAdapter(getActivity().getApplicationContext(), organizationModelList);
        listView.setAdapter(adapter);
        listView.requestFocus(0);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Fragment frag = new OranizationDetailList();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_framelayout, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }

        });

    }

}