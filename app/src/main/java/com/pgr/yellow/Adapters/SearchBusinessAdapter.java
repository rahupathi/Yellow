package com.pgr.yellow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgr.yellow.Models.CompanyModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;


import java.util.Locale;

/**
 * Created by Thiyagu on 4/2/2015.
 */
public class SearchBusinessAdapter extends BaseAdapter {
    Context fcontext;
    LayoutInflater inflater;
    private List<CompanyModel> facilitylist=null;
    private ArrayList<CompanyModel> arrayList;

    public SearchBusinessAdapter(Context context, List<CompanyModel> _facilitylist){
        fcontext=context;
        inflater= LayoutInflater.from(context);
        this.facilitylist=_facilitylist;
        this.arrayList=new ArrayList<CompanyModel>();
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

        holder.tvSBFacilityName.setText(facilitylist.get(position).getCompanyName());
        holder.tvSBFacilityAddress.setText(facilitylist.get(position).getAddress());
        holder.tvSBFacilityCity.setText(facilitylist.get(position).getCity());
        return view;
    }
    public void filterData(String query) {

        query = query.toLowerCase(Locale.getDefault());

        facilitylist.clear();
        if (query.isEmpty()) {
            facilitylist.addAll(arrayList);
        } else {
            for (CompanyModel tCompanyModel : facilitylist) {
                if (tCompanyModel.getCompanyName()
                        .toLowerCase(Locale.getDefault()).contains(query)
                        || tCompanyModel.getCompanyName()
                        .toLowerCase(Locale.getDefault())
                        .contains(query)
                        ) {
                    facilitylist.add(tCompanyModel);
                }
            }

        }
        //Log.v("MyListAdapter", String.valueOf(providerList.size()));
        notifyDataSetChanged();

    }

}
