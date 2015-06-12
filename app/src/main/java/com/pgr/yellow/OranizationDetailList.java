package com.pgr.yellow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pgr.yellow.Adapters.OrganizationAdapter;
import com.pgr.yellow.Adapters.OrganizationDetailAdapter;
import com.pgr.yellow.Models.OrganizationModel;

import java.util.ArrayList;

/**
 * Created by RAHUPATHI on 6/12/2015.
 */
public class OranizationDetailList extends Fragment {
    private ListView listView;
    private ArrayList<OrganizationModel> organizationModelList = null;
    private OrganizationDetailAdapter adapter;
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

        listView = (ListView) vDetail.findViewById(R.id.lvOraglistView);
        adapter = new OrganizationDetailAdapter(getActivity().getApplicationContext(), organizationModelList);
        listView.setAdapter(adapter);
        listView.requestFocus(0);
    }
}
