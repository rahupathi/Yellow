package com.pgr.yellow.Activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pgr.yellow.R;

public class ListWithHeaders extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListItemContainer<ListItemObject> data = new ListItemContainer<ListItemObject>();
        data.addData(new ListItemObject("Babbage"));
        data.addData(new ListItemObject("Boole "));
        data.addData(new ListItemObject("Berners-Lee"));
        data.addData(new ListItemObject("Atanasoff "));
        data.addData(new ListItemObject("Allen"));
        data.addData(new ListItemObject("Cormack"));
        data.addData(new ListItemObject("Cray"));
        data.addData(new ListItemObject("Dijkstra "));
        data.addData(new ListItemObject("Dix"));
        data.addData(new ListItemObject("Dewey"));
        data.addData(new ListItemObject("Erdos"));
 
        Map<String, ArrayList<ListItemObject>> sortedContacts = data.getSortedData();
        SeparatedListAdapter adapter = this.makeAdapter(sortedContacts);
        this.setListAdapter(adapter);
       // this.setContentView(R.layout.main);
    }
 
    public <T extends ListItemInterface> SeparatedListAdapter makeAdapter(Map<String, ArrayList<T>> sortedObjects)
    {
        Iterator it = sortedObjects.entrySet().iterator();
        SeparatedListAdapter adapter = new SeparatedListAdapter(this);
        String label = null;
        ArrayList<T> section = new ArrayList<T>();
        while (it.hasNext()) {
            Map.Entry<String,ArrayList<T>> pairs = (Map.Entry<String,ArrayList<T>>) it.next();
            section =  pairs.getValue();
            label = pairs.getKey();
            adapter.addSection(label, new ArrayAdapter<T>(this, R.layout.list_item, section));
        }
        return adapter;
    }
 
}