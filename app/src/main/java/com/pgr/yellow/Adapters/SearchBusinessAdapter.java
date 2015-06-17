package com.pgr.yellow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgr.yellow.Models.OrganizationModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.pgr.yellow.Models.OrganizationModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiyagu on 4/2/2015.
 */
public class SearchBusinessAdapter extends BaseAdapter {
    Context fcontext;
    LayoutInflater inflater;
    private List<OrganizationModel> facilitylist=null;
    private ArrayList<OrganizationModel> arrayList;

    public SearchBusinessAdapter(Context context, List<OrganizationModel> _facilitylist){
        fcontext=context;
        inflater= LayoutInflater.from(context);
        this.facilitylist=_facilitylist;
        this.arrayList=new ArrayList<OrganizationModel>();
        this.arrayList.addAll(facilitylist);
    }
    public class ViewHolder{
        TextView tvSBFacilityName,tvSBFacilityAddress,tvSBFacilityCity;
        ImageView imgLocation;
    }
    @Override
    public int getCount() {
        return facilitylist.size();
    }

    @Override
    public Object getItem(int position) {
        return facilitylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   /* private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Clicked Location",Toast.LENGTH_LONG).show();
            fcontext.startActivity(i);
        }
    };*/

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.searchbusiness_row , null);
            holder.tvSBFacilityName=(TextView)view.findViewById(R.id.tvSBFacilityName);
            holder.tvSBFacilityAddress=(TextView)view.findViewById(R.id.tvSBFacilityAddress);
            holder.tvSBFacilityCity=(TextView)view.findViewById(R.id.tvSBFacilityCity);
            //holder.imgLocation.setOnClickListener(clickListener);
            view.setTag(holder);
        }
        else {
            holder=(ViewHolder)view.getTag();
        }

        holder.tvSBFacilityName.setText(facilitylist.get(position).getFacilityName());
        holder.tvSBFacilityAddress.setText(facilitylist.get(position).getFacilityAddress());
        holder.tvSBFacilityCity.setText(facilitylist.get(position).getCity());
        return view;
    }
}
