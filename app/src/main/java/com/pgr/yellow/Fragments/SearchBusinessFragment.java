package com.pgr.yellow.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.pgr.yellow.Adapters.SearchBusinessAdapter;
import com.pgr.yellow.Models.CompanyModel;
import com.pgr.yellow.R;

import java.util.ArrayList;

/**
 * Created by BMS0020 on 6/17/2015.
 */
public class SearchBusinessFragment extends Fragment {

    private ListView listView;
    private ArrayList<CompanyModel> companyModelList = null;
    private SearchBusinessAdapter adapter;
    private View v;

    private EditText mSearchEt;
    private String mSearchQuery;
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

            mSearchEt = (EditText) v.findViewById(R.id.etBusinessSearch);
            mSearchEt.addTextChangedListener(new SearchWatcher());
            mSearchEt.setText(mSearchQuery);
            mSearchEt.requestFocus();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private class SearchWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence c, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence c, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            mSearchQuery = mSearchEt.getText().toString();
            //adapter.filter(mSearchQuery);
            adapter.filterData(mSearchQuery);
        }

    }
    private void LoadList(){

        companyModelList = new ArrayList<CompanyModel>();
        CompanyModel objOrganizationMode = new CompanyModel();
        objOrganizationMode.setCompanyName("Aannemingsbedrijf RADJ");
        objOrganizationMode.setAddress("Arabistraat perceel 2449 Geyersvljit, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setCompanyName("Aannemingsbedrijf Supra");
        objOrganizationMode.setAddress("Commissaris Weytingwyg 335, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setCompanyName("Alki Bouw & Constructie NY");
        objOrganizationMode.setAddress("Ouderzorgweg 9, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setCompanyName("Antonius Construnctions NV");
        objOrganizationMode.setAddress("Lettitia Vriedselann 16, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        listView = (ListView) v.findViewById(R.id.lvSearchBusiness);
        adapter = new SearchBusinessAdapter(getActivity().getApplicationContext(), companyModelList);
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
