package com.pgr.yellow.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pgr.yellow.Adapters.OrganizationDetailAdapter;
import com.pgr.yellow.Models.CategoryModel;
import com.pgr.yellow.Models.CompanyModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAHUPATHI on 6/12/2015.
 */
public class OranizationDetailList extends Fragment {
    private ListView listView;
    private ArrayList<CompanyModel> companyModelList = null;
    private ArrayList<CategoryModel> categoryModelsDetailList = null;
    private OrganizationDetailAdapter adapter;
    private String mCategoryId;
    View vDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        vDetail = (View) inflater.inflate(R.layout.organizationdetail, container, false);
        return vDetail;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        mCategoryId = getArguments().getString("CategoryId");
        LoadDetailList();
    }

    private void LoadDetailList() {


        companyModelList = new ArrayList<CompanyModel>();
    /*    CompanyModel objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Aannemingsbedrijf RADJ");
        objOrganizationMode.setFacilityAddress("Arabistraat perceel 2449 Geyersvljit, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Aannemingsbedrijf Supra");
        objOrganizationMode.setFacilityAddress("Commissaris Weytingwyg 335, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Alki Bouw & Constructie NY");
        objOrganizationMode.setFacilityAddress("Ouderzorgweg 9, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Antonius Construnctions NV");
        objOrganizationMode.setFacilityAddress("Lettitia Vriedselann 16, Paramaribo");
        objOrganizationMode.setCity("Gesloten");
        companyModelList.add(objOrganizationMode);*/


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Company");
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> companyList, ParseException e) {
                if (e == null) {
                    for (ParseObject company : companyList) {
                        Object sCompany = company.get("name");
                        Object sCompanyId = company.getObjectId();
                        String sCatObjectID = ((ParseObject) company.get("category")).getObjectId().toString();
                        if (sCatObjectID.equalsIgnoreCase(mCategoryId)) {
                            CompanyModel tCompany = new CompanyModel();
                            tCompany.setCompanyID(sCompanyId.toString());
                            tCompany.setCompanyName(sCompany.toString());
                            companyModelList.add(tCompany);
                        }
                    }
                    if (companyModelList.size() > 0) {
                        FillCompanyAddress();
                    }
                } else {
                    Log.e("Parse", "Error: " + e.getMessage());
                }
            }
        });


    }


    private void FillCompanyAddress(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Location");
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> companyList, ParseException e) {
                if (e == null) {
                    for (ParseObject company : companyList) {
                        Object sAddress = company.get("address");
                        Object sCity = company.get("town");
                        Object sPhone = company.get("Phone");
                        Object sFax = company.get("Fax");
                        Object sCompanyId = company.getObjectId();
                        String sCompRelObjectID = ((ParseObject) company.get("company")).getObjectId().toString();
                        if (!sCompRelObjectID.isEmpty()) {
                            for (int i = 0; i < companyModelList.size(); i++) {
                                CompanyModel comp = (CompanyModel) companyModelList.get(i);
                                if (comp.getCompanyID().toString().equalsIgnoreCase(sCompRelObjectID)) {
                                    comp.setAddress(sAddress != null ? sAddress.toString() : "");
                                    comp.setCity(sCity != null ? sCity.toString() : "");
                                    comp.setPhone(sPhone != null ? sPhone.toString() : "");
                                    comp.setFax(sFax != null ? sFax.toString() : "");
                                }
                            }
                        }
                    }
                    listView = (ListView) vDetail.findViewById(R.id.lvOraglistView);
                    adapter = new OrganizationDetailAdapter(getActivity().getApplicationContext(), companyModelList);
                    listView.setAdapter(adapter);
                    listView.requestFocus(0);
                }
                else{
                    Log.e("Parse", "Error: " + e.getMessage());
                }
            }
        });

    }
}
