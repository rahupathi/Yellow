package com.pgr.yellow.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pgr.yellow.Adapters.OrganizationAdapter;
import com.pgr.yellow.Adapters.SearchBusinessAdapter;
import com.pgr.yellow.Models.OrganizationModel;
import com.pgr.yellow.R;

import java.util.ArrayList;

/**
 * Created by BMS0020 on 6/17/2015.
 */
public class SearchBusinessFragment extends Fragment {

    private ListView listView;
    private ArrayList<OrganizationModel> organizationModelList = null;
    private SearchBusinessAdapter adapter;
    private View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = (View) inflater.inflate(R.layout.searchbusiness_fragment, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        try {
            LoadList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void LoadList(){

        organizationModelList = new ArrayList<OrganizationModel>();
        OrganizationModel objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Aannemingsbedrijf RADJ");
        objOrganizationMode.setFacilityAddress("Arabistraat perceel 2449 Geyersvljit, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Aannemingsbedrijf Supra");
        objOrganizationMode.setFacilityAddress("Commissaris Weytingwyg 335, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Alki Bouw & Constructie NY");
        objOrganizationMode.setFacilityAddress("Ouderzorgweg 9, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        organizationModelList.add(objOrganizationMode);

        objOrganizationMode = new OrganizationModel();
        objOrganizationMode.setFacilityName("Antonius Construnctions NV");
        objOrganizationMode.setFacilityAddress("Lettitia Vriedselann 16, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        organizationModelList.add(objOrganizationMode);

        listView = (ListView) v.findViewById(R.id.lvSearchBusiness);
        adapter = new SearchBusinessAdapter(getActivity().getApplicationContext(), organizationModelList);
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
