package sadaka.com.example.android.sadaka.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sadaka.com.example.android.sadaka.DAO.Organization;
import sadaka.com.example.android.sadaka.R;


/**
 * Created by aisha on 8/14/2016.
 */
public class OrgAdapter extends BaseAdapter {
    Activity activity;
    List<Organization> organizations;


    private static LayoutInflater inflater = null;

    public OrgAdapter(Activity activity, List<Organization> organizations
    ) {
        this.activity = activity;
        this.organizations = organizations;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return organizations.size();
    }

    @Override
    public Organization getItem(int i) {
        return organizations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder {
        ImageView img;
        TextView txt;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_org, viewGroup, false);
            holder = new Holder();
            holder.img = (ImageView) view.findViewById(R.id.org_img);
            holder.txt = (TextView) view.findViewById(R.id.org_name);
            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }


        Organization org = organizations.get(i);
        holder.txt.setText(org.getNameEng());
        //first check if i dont have image in lru cahe
        // then call async task and give it it's view
        //OrgImgUrl orgImgUrl=new OrgImgUrl(holder.img,org.getImg());
        //orgImgUrl.execute();
        //all the above replaced with picasso
        Picasso.with(activity).load(org.getImg()).placeholder(R.drawable.sadaka_logo).error(R.drawable.sadaka_logo).into(holder.img);
        return view;
    }


}
