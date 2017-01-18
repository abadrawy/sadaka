package sadaka.com.example.android.sadaka.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import sadaka.com.example.android.sadaka.R;

/**
 * Created by aisha on 9/16/2016.
 */
public class DrawerAdapter extends BaseAdapter{
    Activity activity;
    List<String>options;
    private static LayoutInflater inflater=null;

    public DrawerAdapter(Activity activity, ArrayList<String>options){
        this.activity=activity;
        this.options=options;
        this.inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int i) {
        return options.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder=null;
        if(view==null)
        {
            view=inflater.inflate(R.layout.drawer_item,viewGroup,false);
            holder= new Holder();
            holder.txt=(TextView) view.findViewById(R.id.option);
            view.setTag(holder);

        }
        else {
            holder=(Holder)view.getTag();
        }
        holder.txt=(TextView) view.findViewById(R.id.option);
        holder.txt.setText(options.get(i));
        return view;

    }
    public class Holder{
        TextView txt;

    }
}
