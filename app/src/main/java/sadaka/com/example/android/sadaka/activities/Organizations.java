package sadaka.com.example.android.sadaka.activities;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import sadaka.com.example.android.sadaka.R;
import sadaka.com.example.android.sadaka.adapters.DrawerAdapter;
import sadaka.com.example.android.sadaka.fragments.History;
import sadaka.com.example.android.sadaka.fragments.ListOrg;
import sadaka.com.example.android.sadaka.fragments.Settings;


public class Organizations extends AppCompatActivity {
    String TAG="Organizations";
    DrawerAdapter drawerAdapter;
    DrawerLayout drawerLayout;
    ListView drawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    Settings settings=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        ///first check to see if app is restared then check if there is histryo in saved prefrence
        //if there is not then add one
        if (savedInstanceState == null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if (!(prefs.contains("History"))) {
                //create it
                ObjectMapper mapper = new ObjectMapper();
                //Object to JSON in String
                try {
                    String objInString = mapper.writeValueAsString(new HistoryRecords(new ArrayList<HistoryListItem>()));
                    prefs.edit().putString("History", objInString).apply();
                } catch (Exception e) {
                    Log.d(TAG, "error parsing");
                }
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTitle = getTitle().toString();
        setupDrawer();

        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerAdapter = new DrawerAdapter(this, options());
        drawerList.setAdapter(drawerAdapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                select(i, (String) drawerAdapter.getItem(i));
            }
        });
        select(0, "Organizations");


    }

    public ArrayList<String> options() {
        ArrayList<String> op = new ArrayList<>();
        op.add("Organizations");
        //op.add("Regular Payments");
        op.add("History");
        op.add("Settings");


        return op;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    public void select(int position, String title) {
        Fragment fragment = null;

        switch (position) {
            case 0: {
                fragment = new ListOrg();
                Bundle bundle = new Bundle();
                bundle.putString("path", getFilesDir().getAbsolutePath());
                fragment.setArguments(bundle);
            }
            break;
            case 1:
                fragment = new History();
                break;
           /*case 2:
               fragment = new RegularPayments();
               break;*/
            case 2:
                fragment =settings= new Settings();
                break;


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            drawerList.setItemChecked(position, true);
            setTitle(title);
            drawerLayout.closeDrawer(drawerList);

        } else {
            Log.e(TAG, "Error in creating fragment");
        }
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG,"onreqest");
        //dening after allowing crahses the app
        //denig after allow restarts the app
        switch (requestCode) {
            case 1:
                Log.d(TAG,"case 1");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"granted");
                    if(settings!=null)
                    settings.ToggleCheckBox(true);
                }
                else {
                    Log.d(TAG,"denied");
                    if(settings!=null)
                    settings.ToggleCheckBox(false);
                }
                return;
        }
    }

}
