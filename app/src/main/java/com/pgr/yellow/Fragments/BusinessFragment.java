package com.pgr.yellow.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pgr.yellow.Adapters.BusinessListAdapter;
import com.pgr.yellow.Models.CompanyModel;
import com.pgr.yellow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.pgr.yellow.Adapters.BusinessListAdapter.*;

/**
 * Created by RAHUPATHI on 6/13/2015.
 */
public class BusinessFragment extends Fragment {

    private BusinessListAdapter adapter = new BusinessListAdapter();
    private GestureDetector mGestureDetector;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private int sideIndexHeight;
    private static float sideIndexX;
    private static float sideIndexY;
    private int indexListSize;
    private ListView listview;
    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v = (View) inflater.inflate(R.layout.business_fragments, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        try {
            //Intent intent = new Intent(getActivity().getApplicationContext(),ListWithHeaders.class);
            //startActivity(intent);
            //ListItemsLoad();
            LoadList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  /*  private void ListItemsLoad() {
        try {
            listview = (ListView) v.findViewById(R.id.lvBusinessList);

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
            data.addData(new ListItemObject("Erodos"));

            Map<String, ArrayList<ListItemObject>> sortedContacts = data.getSortedData();
            SeparatedListAdapter adapter = this.makeAdapter(sortedContacts);
            if (listview != null)
                listview.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public <T extends ListItemInterface> SeparatedListAdapter makeAdapter(Map<String, ArrayList<T>> sortedObjects) {
        Iterator it = sortedObjects.entrySet().iterator();
        SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
        String label = null;
        ArrayList<T> section = new ArrayList<T>();
        while (it.hasNext()) {
            Map.Entry<String, ArrayList<T>> pairs = (Map.Entry<String, ArrayList<T>>) it.next();
            section = pairs.getValue();
            label = pairs.getKey();
            adapter.addSection(label, new ArrayAdapter<T>(getActivity(), R.layout.list_item, section));
        }
        return adapter;
    }
*/

    public class SideIndexGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            sideIndexX = sideIndexX - distanceX;
            sideIndexY = sideIndexY - distanceY;

            if (sideIndexX >= 0 && sideIndexY >= 0) {
                displayListItem();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }


    private void LoadList() {

        alphabet = new ArrayList<Object[]>();
        listview = (ListView) v.findViewById(R.id.lvBusinessList);

        mGestureDetector = new GestureDetector(getActivity(), new SideIndexGestureListener());

        //List<String> countries = populateCountries();

        List<CompanyModel> countries = populateCountries();
        //Collections.sort(countries);

        //List<Row> rows = new ArrayList<Row>();
        List<Row> rows = new ArrayList<Row>();
        int start = 0;
        int end = 0;
        String previousLetter = null;
        Object[] tmpIndexItem = null;
        Pattern numberPattern = Pattern.compile("[0-9]");

        for (CompanyModel country : countries) {
            String firstLetter = country.getAlphabets().substring(0, 1);

            // Group numbers together in the scroller
            if (numberPattern.matcher(firstLetter).matches()) {
                firstLetter = "#";
            }

            // If we've changed to a new letter, add the previous letter to the alphabet scroller
            if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                end = rows.size() - 1;
                tmpIndexItem = new Object[3];
                tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
                tmpIndexItem[1] = start;
                tmpIndexItem[2] = end;
                alphabet.add(tmpIndexItem);

                start = end + 1;
            }

            // Check if we need to add a header row
            if (!firstLetter.equals(previousLetter)) {
                if (country.getCompanyName() != null) {
                    rows.add(new Section(firstLetter));
                    sections.put(firstLetter, start);
                }
            }
            // Add the country to the list
            if (country.getCompanyName() != null) {
                rows.add(new Item(country.getCompanyName(), country.getCompanyName(), firstLetter));
            }
            previousLetter = firstLetter;
        }

        if (previousLetter != null) {
            // Save the last letter
            tmpIndexItem = new Object[3];
            tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
            tmpIndexItem[1] = start;
            tmpIndexItem[2] = rows.size() - 1;
            alphabet.add(tmpIndexItem);
        }

        adapter.setRows(rows);
        listview.setAdapter(adapter);
        updateList();
        //listview.setOnTouchListener(new );
    }

    public void updateList() {
        LinearLayout sideIndex = (LinearLayout) v.findViewById(R.id.sideIndex);
        //sideIndex.removeAllViews();
        indexListSize = alphabet.size();
        if (indexListSize < 1) {
            return;
        }

        int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);
        int tmpIndexListSize = indexListSize;
        while (tmpIndexListSize > indexMaxSize) {
            tmpIndexListSize = tmpIndexListSize / 2;
        }
        double delta;
        if (tmpIndexListSize > 0) {
            delta = indexListSize / tmpIndexListSize;
        } else {
            delta = 1;
        }

        TextView tmpTV;
        if (sideIndex.getChildCount() == 0) {
            for (double i = 1; i <= indexListSize; i = i + delta) {
                Object[] tmpIndexItem = alphabet.get((int) i - 1);
                String tmpLetter = tmpIndexItem[0].toString();

                tmpTV = new TextView(getActivity());
                tmpTV.setText(tmpLetter);
                tmpTV.setTextColor(Color.parseColor("#ffffff"));
                tmpTV.setGravity(Gravity.CENTER);
                tmpTV.setTextSize(12);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                tmpTV.setLayoutParams(params);
                sideIndex.addView(tmpTV);
            }

            sideIndexHeight = sideIndex.getHeight();

            sideIndex.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // now you know coordinates of touch
                    sideIndexX = event.getX();
                    sideIndexY = event.getY();

                    // and can display a proper item it country list
                    displayListItem();

                    return false;
                }
            });
        }
    }

    public void displayListItem() {
        LinearLayout sideIndex = (LinearLayout) v.findViewById(R.id.sideIndex);
        sideIndexHeight = sideIndex.getHeight();
        // compute number of pixels for every side index item
        double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

        // compute the item index for given event position belongs to
        int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

        // get the item (we can do it since we know item index)
        if (itemPosition < alphabet.size()) {
            Object[] indexItem = alphabet.get(itemPosition);
            int subitemPosition;
            try {
                subitemPosition = sections.get(indexItem[0]);
            } catch (Exception ex) {
                subitemPosition = -1;
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            //ListView listView = (ListView) findViewById(android.R.id.list);
            if (subitemPosition > 0)
                listview.setSelection(subitemPosition);

        }
    }

    private List<CompanyModel> populateCountries() {

        //List<String> countries = new ArrayList<String>();
        List<CompanyModel> countries = new ArrayList<CompanyModel>();
        CompanyModel orgModel = new CompanyModel();
        ArrayList<String> alphabets = new ArrayList<String>();
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("C");
        alphabets.add("D");
        alphabets.add("E");
        alphabets.add("F");
        alphabets.add("G");
        alphabets.add("H");
        alphabets.add("I");
        alphabets.add("J");
        alphabets.add("K");
        alphabets.add("L");
        alphabets.add("M");
        alphabets.add("N");
        alphabets.add("O");
        alphabets.add("P");
        alphabets.add("Q");
        alphabets.add("R");
        alphabets.add("S");
        alphabets.add("T");
        alphabets.add("U");
        alphabets.add("V");
        alphabets.add("X");
        alphabets.add("Y");
        alphabets.add("Z");
        alphabets.add("0");
        alphabets.add("2");
        alphabets.add("9");

        for (String alpha : alphabets) {
            orgModel = new CompanyModel();
            orgModel.setAlphabets(alpha);
            countries.add(orgModel);
        }

        countries.get(0).setCompanyName("Afghanistan");
        countries.get(1).setCompanyName("Albania");
        countries.get(2).setCompanyName("Bahrain");
        countries.get(3).setCompanyName("Bangladesh");
        countries.get(4).setCompanyName("Cambodia");
        countries.get(5).setCompanyName("Cameroon");


        /*countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Bahrain");
        countries.add("Bangladesh");
        countries.add("Cambodia");
        countries.add("Cameroon");
        countries.add("Denmark");
        countries.add("Djibouti");
        countries.add("East Timor");
        countries.add("Ecuador");
        countries.add("Fiji");
        countries.add("Finland");
        countries.add("Gabon");
        countries.add("Georgia");
        countries.add("Haiti");
        countries.add("Holy See");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Jamaica");
        countries.add("Japan");
        countries.add("Kazakhstan");
        countries.add("Kenya");
        countries.add("Laos");
        countries.add("Latvia");
        countries.add("Macau");
        countries.add("Macedonia");
        countries.add("Namibia");
        countries.add("Nauru");
        countries.add("Oman");
        countries.add("Pakistan");
        countries.add("Palau");
        countries.add("Qatar");
        countries.add("Romania");
        countries.add("Russia");
        countries.add("Saint Kitts and Nevis");
        countries.add("Saint Lucia");
        countries.add("Taiwan");
        countries.add("Tajikistan");
        countries.add("Uganda");
        countries.add("Ukraine");
        countries.add("Vanuatu");
        countries.add("Venezuela");
        countries.add("Yemen");
        countries.add("Zambia");
        countries.add("Zimbabwe");
        countries.add("0");
        countries.add("2");
        countries.add("9");*/

        return countries;
    }

}
