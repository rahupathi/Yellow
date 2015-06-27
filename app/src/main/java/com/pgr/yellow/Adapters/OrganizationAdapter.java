package com.pgr.yellow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.pgr.yellow.Models.CategoryModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiyagu on 4/2/2015.
 */
public class OrganizationAdapter extends BaseAdapter {
    Context fcontext;
    LayoutInflater inflater;
    private List<CategoryModel> facilitylist=null;
    private ArrayList<CategoryModel> arrayList;

    public OrganizationAdapter(Context context, List<CategoryModel> _facilitylist){
        fcontext=context;
        inflater= LayoutInflater.from(context);
        this.facilitylist=_facilitylist;
        this.arrayList=new ArrayList<CategoryModel>();
        this.arrayList.addAll(facilitylist);
    }
    public class ViewHolder{
        TextView txtFacilityName,txtCity,txtPhone,txtStateAndZip;
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
            view=inflater.inflate(R.layout.organization_row , null);
            holder.txtFacilityName=(TextView)view.findViewById(R.id.tvFacilityName);
            holder.txtCity=(TextView)view.findViewById(R.id.tvFacilityCity);
            //holder.imgLocation.setOnClickListener(clickListener);
            view.setTag(holder);
        }
        else {
            holder=(ViewHolder)view.getTag();
        }

        holder.txtFacilityName.setText(facilitylist.get(position).getCategoryName());
        holder.txtCity.setText(String.valueOf(facilitylist.get(position).getCompanyCount())+ " locations");
        return view;
    }
}
