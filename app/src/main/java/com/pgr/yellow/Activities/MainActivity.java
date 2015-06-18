package com.pgr.yellow.Activities;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.pgr.yellow.Fragments.Tab4Container;
import com.pgr.yellow.R;
import com.pgr.yellow.Fragments.Tab1Container;
import com.pgr.yellow.Fragments.Tab2Container;
import com.pgr.yellow.Fragments.Tab3Container;

public class MainActivity extends ActionBarActivity   {


    private static final String TAB_1_TAG = "tab_1";
    private static final String TAB_2_TAG = "tab_2";
    private static final String TAB_3_TAG = "tab_3";
    private static final String TAB_4_TAG = "tab_4";
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        InitView();
    }

    @Override
    public void onBackPressed() {
        boolean isPopFragment = false;
        String currentTabTag = mTabHost.getCurrentTabTag();

        if (currentTabTag.equals(TAB_1_TAG)) {
            isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_1_TAG)).popFragment();
        } else if (currentTabTag.equals(TAB_2_TAG)) {
            isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_2_TAG)).popFragment();
        } else if (currentTabTag.equals(TAB_3_TAG)) {
            isPopFragment = ((BaseContainerFragment) getSupportFragmentManager().findFragmentByTag(TAB_3_TAG)).popFragment();
        }

        if (!isPopFragment) {
            finish();
        }
    }

    private void InitView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(setIndicator(MainActivity.this, mTabHost.newTabSpec(TAB_1_TAG),
                R.drawable.tab_indicator_gen, "Categorieen", R.drawable.tab_action_select, TAB_1_TAG), Tab1Container.class, null);
        mTabHost.addTab(setIndicator(MainActivity.this,mTabHost.newTabSpec(TAB_2_TAG),
                R.drawable.tab_indicator_gen,"Bedrijven",R.drawable.tab_company_select,TAB_2_TAG),Tab2Container.class,null);
        mTabHost.addTab(setIndicator(MainActivity.this, mTabHost.newTabSpec(TAB_3_TAG),
                R.drawable.tab_indicator_gen, "Snel Bellen", R.drawable.tab_customer_select, TAB_3_TAG), Tab3Container.class, null);
        mTabHost.addTab(setIndicator(MainActivity.this, mTabHost.newTabSpec(TAB_4_TAG),
                R.drawable.tab_indicator_gen, "Zoek", R.drawable.tab_search_select, TAB_4_TAG), Tab4Container.class, null);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ChangeTextViewColor(tabId);
            }
        });
        TextView tv =(TextView)mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.txt_tabtxt);
        if (tv != null) {
            tv.setTextColor(Color.parseColor("#FACF2D"));
        }
    }
    private void ChangeTextViewColor(String currentTa1b){

        //((TextView)mTabHost.getTabWidget().getChildAt(0).get
        //((TextView)mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.txt_tabtxt)).setTextColor(Color.parseColor("#FACF2D"));
        //((TextView)mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.txt_tabtxt)).setTextColor(Color.parseColor("#FFF"));
        //((TextView)mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.txt_tabtxt)).setTextColor(Color.parseColor("#FFF"));
        //((TextView)mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.txt_tabtxt)).setTextColor(Color.parseColor("#FFF"));
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            View tabView = mTabHost.getTabWidget().getChildAt(i);
            if (tabView!=null) {
                TextView tv = ((TextView) tabView.findViewById(R.id.txt_tabtxt));
                if (tv != null) {
                    if (String.valueOf(tv.getTag()).equalsIgnoreCase(currentTa1b)) {
                        tv.setTextColor(Color.parseColor("#FACF2D"));
                    }
                    else {
                        tv.setTextColor(Color.parseColor("#ffffff"));
                    }
                }
            }
        }
    }
    private TabSpec setIndicator(Context ctx, TabSpec spec,
                                 int resid, String string, int genresIcon,String TAG_ID) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
        v.setBackgroundResource(resid);
        TextView tv = (TextView)v.findViewById(R.id.txt_tabtxt);
        ImageView img = (ImageView)v.findViewById(R.id.img_tabtxt);

        tv.setText(string);
        tv.setTag(TAG_ID);
        img.setBackgroundResource(genresIcon);
        return spec.setIndicator(v);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
