package com.pgr.yellow.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgr.yellow.Models.ProductGroupModel;
import com.pgr.yellow.Models.ProductModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerSupportGroupAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private ArrayList<ProductGroupModel> providerList;
    private ArrayList<ProductGroupModel> originalList;

    public CustomerSupportGroupAdapter(Activity context, List<ProductGroupModel> mProductList) {
        this.context = context;
        this.providerList = new ArrayList<ProductGroupModel>();
        this.providerList.addAll(mProductList);
        this.originalList = new ArrayList<ProductGroupModel>();
        this.originalList.addAll(mProductList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ProductModel> countryList = providerList.get(groupPosition)
                .getProductList();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        // LayoutInflater inflater = context.getLayoutInflater();
        ProductModel tMember = (ProductModel) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.product_row,
                    null);
        }

        TextView tvProductName = (TextView) convertView
                .findViewById(R.id.tvProductName);
        TextView tvProductCount = (TextView) convertView
                .findViewById(R.id.tvProductCount);

        tvProductName.setText(tMember.getProductName());
        tvProductCount.setText(String.valueOf( tMember.getProductCount()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ProductModel> countryList = providerList.get(groupPosition)
                .getProductList();
        return countryList.size();

    }

    public Object getGroup(int groupPosition) {
        return providerList.get(groupPosition);
    }

    public int getGroupCount() {
        return providerList.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        ProductGroupModel objProviderGroupModel = (ProductGroupModel) getGroup(groupPosition);

        ImageView image = null;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.product_group_item,
                    null);
        }
        TextView item = (TextView) convertView
                .findViewById(R.id.tvCategoryGroupName);


        /*if (groupPosition != 0) {
            int imageResourceId = isExpanded ? R.drawable.ic_action_uparrow_white
                    : R.drawable.ic_action_downarrow_white;
            image.setImageResource(imageResourceId);

            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.INVISIBLE);
        }*/

        item.setText(objProviderGroupModel.getName());
        // item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {

        query = query.toLowerCase(Locale.getDefault());

        providerList.clear();
        if (query.isEmpty()) {
            providerList.addAll(originalList);
        } else {

            for (ProductGroupModel continent : originalList) {

                ArrayList<ProductModel> countryList = continent.getProductList();
                ArrayList<ProductModel> newList = new ArrayList<ProductModel>();
                for (ProductModel tMembers : countryList) {
                    if (tMembers.getProductName()
                            .toLowerCase(Locale.getDefault()).contains(query)
                            || tMembers.getProductName()
                            .toLowerCase(Locale.getDefault())
                            .contains(query)
                            ) {
                        newList.add(tMembers);
                    }
                }
                if (newList.size() > 0) {
                    ProductGroupModel nContinent = new ProductGroupModel(
                            continent.getName(), newList);
                    providerList.add(nContinent);
                }
            }
        }
        //Log.v("MyListAdapter", String.valueOf(providerList.size()));
        notifyDataSetChanged();

    }

}
