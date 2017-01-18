package sadaka.com.example.android.sadaka.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import sadaka.com.example.android.sadaka.R;
import sadaka.com.example.android.sadaka.activities.HistoryRecords;
import sadaka.com.example.android.sadaka.adapters.HistoryAdapter;

/**
 * Created by aisha on 9/16/2016.
 */
public class History extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history, container, false);
        ListView listView=(ListView)rootView.findViewById(R.id.historyRecords);
        //get the history from shared pref
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String objStr=pref.getString("History","");
        //parse back to object
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            HistoryRecords historyRecords=objectMapper.readValue(objStr,HistoryRecords.class);
            HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),historyRecords.getHistory());
            listView.setAdapter(historyAdapter);
        }
        catch(Exception e){
            Log.d("HistoryFragment","error parsing json");
        }




        return rootView;
    }
}
