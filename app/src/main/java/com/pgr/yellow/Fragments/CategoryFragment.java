package com.pgr.yellow.Fragments;

/**
 * Created by BMS0020 on 6/11/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pgr.yellow.Adapters.OrganizationAdapter;
import com.pgr.yellow.Models.CategoryModel;
import com.pgr.yellow.Models.CompanyModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private ListView listView;
    private ArrayList<CompanyModel> companyModelList = null;
    private OrganizationAdapter adapter;
    View v;
    ArrayList<CategoryModel> categoryModelsList = null;

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
        //LoadList();
        GetCategorList();
    }

    public void GetCategorList() {

        categoryModelsList = new ArrayList<CategoryModel>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CategoryLocale");
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> categoryList, ParseException e) {
                if (e == null) {
                    for (ParseObject category : categoryList) {
                        Object sCategoryCount = category.get("en");
                        Object sObjectId = category.getObjectId();
                        if (sCategoryCount != null) {
                            CategoryModel categoryModel = new CategoryModel(sCategoryCount.toString(), 0, sObjectId.toString());
                            categoryModelsList.add(categoryModel);
                        }
                    }
                    if (categoryModelsList.size() > 0) {
                        GetCategoryCount();
                    }
                } else {
                    Log.e("Parse", "Error: " + e.getMessage());
                }
            }
        });
    }

    private int GetCategoryCount() {
        int iReturn = 0;
        try {


            ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
            query.setLimit(100);
            //query.include("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> categoryCount, ParseException e) {
                    if (e == null) {
                        for (ParseObject category : categoryCount) {
                            if (category.get("companyCount") != null) {
                                Integer iCompanycount = Integer.valueOf(category.get("companyCount").toString());
                                Object obName = category.get("name");
                                if (obName != null && iCompanycount > 0) {
                                    String sObjectID = ((ParseObject) obName).getObjectId().toString();
                                    for (int i = 0; i < categoryModelsList.size(); i++) {
                                        CategoryModel cat = (CategoryModel) categoryModelsList.get(i);
                                        if (cat.getObjectId().equalsIgnoreCase(sObjectID)) {
                                            cat.setCompanyCount(iCompanycount);
                                            cat.setObjectId(category.getObjectId().toString());
                                        }
                                    }
                                }

                            }
                        }

                        listView = (ListView) v.findViewById(R.id.lvFacilitylistView);
                        adapter = new OrganizationAdapter(getActivity().getApplicationContext(), categoryModelsList);
                        listView.setAdapter(adapter);
                        listView.requestFocus(0);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                final CategoryModel mCategoryModel = (CategoryModel) parent.getItemAtPosition(position);
                                if (mCategoryModel != null) {
                                    Fragment frag = new OranizationDetailList();
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.container_framelayout, frag);
                                    Bundle args = new Bundle();
                                    args.putString("CategoryId", mCategoryModel.getObjectId());
                                    frag.setArguments(args);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                }
                            }

                        });

                    } else {
                        Log.e("Parse", "Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception ex) {

        }
        return 0;
    }

    private void LoadList() {

      /*companyModelList  companyModelList = new ArrayList<CompanyModel>();
        CompanyModel objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Annemers");
        objOrganizationMode.setCity("5 bedrijven");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Accountants");
        objOrganizationMode.setCity("4 bedrijven");
        companyModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Constructions");
        objOrganizationMode.setCity("2 bedrijven");
        compnayModelList.add(objOrganizationMode);

        objOrganizationMode = new CompanyModel();
        objOrganizationMode.setFacilityName("Vehicle Service");
        objOrganizationMode.setCity("3 bedrijven");
        compnayModelList.add(objOrganizationMode);*/

        listView = (ListView) v.findViewById(R.id.lvFacilitylistView);
        adapter = new OrganizationAdapter(getActivity().getApplicationContext(), categoryModelsList);
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