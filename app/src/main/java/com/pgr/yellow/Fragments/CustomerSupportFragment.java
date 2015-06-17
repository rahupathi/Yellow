package com.pgr.yellow.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.pgr.yellow.Adapters.CustomerSupportGroupAdapter;
import com.pgr.yellow.Models.ProductGroupModel;
import com.pgr.yellow.Models.ProductModel;
import com.pgr.yellow.R;

import java.util.ArrayList;

/**
 * Created by BMS0020 on 6/17/2015.
 */
public class CustomerSupportFragment extends Fragment {


    private ArrayList<ProductGroupModel> continentList = new ArrayList<ProductGroupModel>();
    private ExpandableListView expListView;
    private CustomerSupportGroupAdapter expListAdapter;

    private View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = (View) inflater.inflate(R.layout.activity_product_expand_list, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        try {

            PrepareProductGroupListView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void expandAll() {
        int count = expListAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            expListView.expandGroup(i);
        }
    }

    private void collapseAll() {
        int count = expListAdapter.getGroupCount();
        if (count > 0) {
            for (int i = 1; i < count; i++) {
                expListView.collapseGroup(i);

            }
        }
    }

    private void PrepareProductGroupListView() {

        try {
            ArrayList<ProductModel> arrProducts = new ArrayList<ProductModel>();
            /*Filter<FacilityModel,String> filter = new Filter<FacilityModel,String>() {
                public boolean isMatched(FacilityModel object, String text) {
                    return object.getFacilityName().startsWith(String.valueOf(text));
                }
            };*/

            ProductModel objProduct = new ProductModel();
            objProduct.setCategoryId(1);
            objProduct.setCategoryName("Alarmnummers");
            objProduct.setProductId(1);
            objProduct.setProductName("Brandweer");
            objProduct.setProductCount(110);
            arrProducts.add(objProduct);

            objProduct = new ProductModel();
            objProduct.setCategoryId(1);
            objProduct.setCategoryName("Alarmnummers");
            objProduct.setProductId(2);
            objProduct.setProductName("Polite");
            objProduct.setProductCount(115);
            arrProducts.add(objProduct);


            objProduct = new ProductModel();
            objProduct.setCategoryId(1);
            objProduct.setCategoryName("Alarmnummers");
            objProduct.setProductId(3);
            objProduct.setProductName("Spoed Eiesende Hulp");
            objProduct.setProductCount(113);
            arrProducts.add(objProduct);

            objProduct = new ProductModel();
            objProduct.setCategoryId(2);
            objProduct.setCategoryName("Storingen");
            objProduct.setProductId(4);
            objProduct.setProductName("EBS");
            objProduct.setProductCount(472828);
            arrProducts.add(objProduct);

            objProduct = new ProductModel();
            objProduct.setCategoryId(2);
            objProduct.setCategoryName("Storingen");
            objProduct.setProductId(5);
            objProduct.setProductName("Telesur");
            objProduct.setProductCount(107);
            arrProducts.add(objProduct);


            objProduct = new ProductModel();
            objProduct.setCategoryId(2);
            objProduct.setCategoryName("Storingen");
            objProduct.setProductId(6);
            objProduct.setProductName("SWM");
            objProduct.setProductCount(471414);
            arrProducts.add(objProduct);

            if (arrProducts.size() > 0) {
                String mCategoryName = "";
                continentList = new ArrayList<ProductGroupModel>();
                ArrayList<ProductModel> lstGroupItems = new ArrayList<ProductModel>();

                for (int iFacilityIndex = 0; iFacilityIndex < arrProducts.size(); iFacilityIndex++) {

                    if (mCategoryName.isEmpty()) {
                        mCategoryName = arrProducts.get(iFacilityIndex).getCategoryName();
                    }

                    if (mCategoryName.equalsIgnoreCase(arrProducts.get(iFacilityIndex).getCategoryName())) {
                        lstGroupItems.add(arrProducts.get(iFacilityIndex));
                    } else {
                        ProductGroupModel mProviderByGroup = new ProductGroupModel(mCategoryName,
                                lstGroupItems);
                        continentList.add(mProviderByGroup);
                        mCategoryName = arrProducts.get(iFacilityIndex).getCategoryName();
                        lstGroupItems = new ArrayList<ProductModel>();
                        lstGroupItems.add(arrProducts.get(iFacilityIndex));
                    }
                }
                if (lstGroupItems.size() > 0) {
                    ProductGroupModel mProviderByGroup = new ProductGroupModel(mCategoryName,
                            lstGroupItems);
                    continentList.add(mProviderByGroup);
                }
            }

            expListView = (ExpandableListView) v.findViewById(R.id.lvGroupProviderList);

            expListView.setGroupIndicator(null);
            expListAdapter = new CustomerSupportGroupAdapter(getActivity(), continentList);
            expListView.setAdapter(expListAdapter);
            // setGroupIndicatorToRight();
            if (expListAdapter.getChildrenCount(0) >= 1) {
                expListView.expandGroup(0);
            }
            expandAll();
            expListView
                    .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent,
                                                    View v, int groupPosition, long id) {
                            if (groupPosition == 0) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });


            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    try {
                        final ProductModel selected = (ProductModel) expListAdapter.getChild(
                                groupPosition, childPosition);
                        //ProviderModel tProvider = (ProviderModel) expListView.getItemAtPosition(childPosition);
                        if (selected != null) {

                            Bundle b = new Bundle();

                        /*b.putSerializable("Provider", selected);
                        Intent iAppointment = new Intent(ProviderList.this,
                                Appointment.class);
                        iAppointment.putExtras(b);
                        iAppointment.putExtra("AppDate",date);
                        startActivity(iAppointment);*/
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                    return true;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

}
