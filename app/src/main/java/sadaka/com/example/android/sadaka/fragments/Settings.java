package sadaka.com.example.android.sadaka.fragments;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import sadaka.com.example.android.sadaka.R;

/**
 * Created by aisha on 9/16/2016.
 */
public class Settings extends Fragment {
    String TAG="Settings";
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.settings, container, false);
         checkBox = (CheckBox) rootView.findViewById(R.id.checkbox);
        //getActivty works but not getContext
        //gave permission mak sure the bc is checked
        //denied make usre the box is unchecked
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.RECEIVE_SMS},
                        1);

            }

        });

        return rootView;
    }
   public void ToggleCheckBox(boolean state){
       checkBox.setChecked(state);
   }

}
