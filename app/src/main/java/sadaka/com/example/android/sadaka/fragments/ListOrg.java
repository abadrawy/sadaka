package sadaka.com.example.android.sadaka.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.List;
import sadaka.com.example.android.sadaka.DAO.Organization;
import sadaka.com.example.android.sadaka.DAO.OrganizationDAO;
import sadaka.com.example.android.sadaka.R;
import sadaka.com.example.android.sadaka.activities.ViewOrganization;
import sadaka.com.example.android.sadaka.adapters.OrgAdapter;

/**
 * Created by aisha on 9/24/2016.
 */
public class ListOrg extends Fragment{
    OrganizationDAO organizationDAO;
    GridView organizationList;
    OrgAdapter orgAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_org, container, false);
        String path=(String)getArguments().get("path");

        organizationDAO=new OrganizationDAO(getActivity(),path);
        organizationList=(GridView)rootView.findViewById(R.id.list_org);
        fillAdapter();
        organizationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent transition = new Intent(getActivity(), ViewOrganization.class);
                transition.putExtra("org",orgAdapter.getItem(i));

                startActivity(transition);
            }
        });

        return rootView;

    }
    public void fillAdapter(){
        List<Organization> orgs=organizationDAO.getOrganizations();
        orgAdapter=new OrgAdapter(getActivity(),orgs);
        organizationList.setAdapter(orgAdapter);
    }


}