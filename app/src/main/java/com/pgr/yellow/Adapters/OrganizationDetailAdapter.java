package com.pgr.yellow.Adapters;

/**
 * Created by RAHUPATHI on 6/12/2015.
 */

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
public class OrganizationDetailAdapter extends BaseAdapter {
    Context fcontext;
    LayoutInflater inflater;
    private List<OrganizationModel> facilitylist=null;
    private ArrayList<OrganizationModel> arrayList;

    public OrganizationDetailAdapter(Context context, List<OrganizationModel> _facilitylist){
        fcontext=context;
        inflater= LayoutInflater.from(context);
        this.facilitylist=_facilitylist;
        this.arrayList=new ArrayList<OrganizationModel>();
        this.arrayList.addAll(facilitylist);
    }
    public class ViewHolder{
        TextView tvOrgName,tvOrgAddress,tvOrgCity;
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
            view=inflater.inflate(R.layout.organization_details_row , null);
            holder.tvOrgName=(TextView)view.findViewById(R.id.tvOrgName);
            holder.tvOrgAddress=(TextView)view.findViewById(R.id.tvOrgAddress);
            holder.tvOrgCity=(TextView)view.findViewById(R.id.tvOrgCity);
            //holder.imgLocation.setOnClickListener(clickListener);
            view.setTag(holder);
        }
        else {
            holder=(ViewHolder)view.getTag();
        }

        holder.tvOrgName.setText(facilitylist.get(position).getFacilityName());
        holder.tvOrgAddress.setText(facilitylist.get(position).getFacilityAddress());
        holder.tvOrgCity.setText(facilitylist.get(position).getCity());
        return view;
    }
}
