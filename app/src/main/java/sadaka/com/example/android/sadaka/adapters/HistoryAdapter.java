package sadaka.com.example.android.sadaka.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import sadaka.com.example.android.sadaka.R;
import sadaka.com.example.android.sadaka.activities.HistoryListItem;

/**
 * Created by aisha on 1/18/2017.
 */
public class HistoryAdapter extends BaseAdapter {
    Activity activity;
    List<HistoryListItem> historyRecords;


    private static LayoutInflater inflater=null;
    public HistoryAdapter(Activity activity,List<HistoryListItem> historyRecords
    ){
        this.activity=activity;
        this.historyRecords=historyRecords;
        this.inflater=( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return historyRecords.size();
    }

    @Override
    public HistoryListItem getItem(int i) {
        return historyRecords.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder{
        TextView date;
        TextView orgName;
        TextView amount;


    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder=null;
        if(view==null)
        {
            view=inflater.inflate(R.layout.history_record,viewGroup,false);
            holder= new Holder();
            holder.date=(TextView) view.findViewById(R.id.date);
            holder.orgName=(TextView) view.findViewById(R.id.org_name);
            holder.amount=(TextView) view.findViewById(R.id.amount);
            view.setTag(holder);

        }
        else {
            holder=(Holder)view.getTag();
        }


        HistoryListItem historyListItem= historyRecords.get(i);

            holder.date.setText(historyListItem.getDate());
            holder.orgName.setText(historyListItem.getOrgName());
            holder.amount.setText(historyListItem.getAmount()+"");



        return view;
    }


}
