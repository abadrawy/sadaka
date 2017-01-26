package sadaka.com.example.android.sadaka.activities;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import sadaka.com.example.android.sadaka.DAO.Organization;
import sadaka.com.example.android.sadaka.R;
import sadaka.com.example.android.sadaka.Recivers.SmsReceiver;

public class ViewOrganization extends AppCompatActivity {
    String TAG="ViewOrganization";
    Activity activity;
    Organization org;
    NumberPicker np;
    int amount;
    SharedPreferences pref;
    HistoryRecords historyRecords = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_org);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        activity = this;
        Intent intent = getIntent();
        org = intent.getParcelableExtra("org");
        np = (NumberPicker) findViewById(R.id.np);
        Button donate = (Button) findViewById(R.id.donate);
        ImageButton phone = (ImageButton) findViewById(R.id.phone);
        ImageButton website = (ImageButton) findViewById(R.id.website);
        ImageButton facebook = (ImageButton) findViewById(R.id.facebook);
        TextView desc = (TextView) findViewById(R.id.desc);
        ImageView img = (ImageView) findViewById(R.id.org);
        Picasso.with(activity).load(org.getImg()).placeholder(R.drawable.sadaka_logo).error(R.drawable.sadaka_logo).into(img);
        desc.setText(org.getDescEng());
        np.setMinValue(1);
        np.setMaxValue(20);
        String[] values = {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
        np.setDisplayedValues(values);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use intent to call
                if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CALL_PHONE},
                            1);


                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + org.getNumber()));
                    startActivity(callIntent);
                }


            }

        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //re direct to website
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(org.getWebsite()));
                startActivity(intent);


            }

        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open in app else redirect to website
                PackageManager packageManager = activity.getPackageManager();
                Intent intent;
               /* try {
                    int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://facewebmodal/f?href=" + org.getFacebook()));
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {*/
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(org.getFacebook()));
                    startActivity(intent);

               // }


            }


        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //a popp for the user asking if he is usre about donating this amount and if i get the ok
                //when i had the builder take in the context it did not work
                //still dont understand why it works with acttivity
                amount = np.getValue();

                String objString = pref.getString("History", "");
                ObjectMapper mapper = new ObjectMapper();

                try {
                    historyRecords = mapper.readValue(objString, HistoryRecords.class);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());

                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setTitle("confirmation");
                alertDialogBuilder.setMessage("confrim donateing " + (amount * 5));
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //if yes i will do the follwoign
                        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS},
                                    2);

                        } else {
                            for (int j = 0; j < amount * 5; j += 5)
                                donateAction();
                            saveRecord();


                        }

                    }
                });

                alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }

        });


    }

    public void donateAction() {
        /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + org.getSmsNum()));
        sendIntent.putExtra("sms_body", org.getSmsText());
        startActivity(sendIntent);*/
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(org.getSmsNum(), null, org.getSmsText() + "", null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void saveRecord() {
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        if (historyRecords != null) {
            HistoryListItem historyListItem = new HistoryListItem(org.getNameEng(), dateString, amount * 5);
            historyRecords.addHistory(historyListItem);
            ObjectMapper mapper = new ObjectMapper();
            try {
                String objInString = mapper.writeValueAsString(historyRecords);
                pref.edit().putString("History", objInString).apply();
                Log.d(TAG, historyListItem.getDate());

            } catch (Exception e) {
                Log.d(TAG, "error parsing");
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    Log.d(TAG, "granted");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + org.getNumber()));
                    //so i dont u derstand why i soul check for it again
                    if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                        startActivity(callIntent);

                } else {


                    Toast.makeText(activity, "please give permisson", Toast.LENGTH_LONG);
                }
                return ;
            }
            case 2:
                for (int j = 0; j < np.getValue() * 5; j += 5)
                    donateAction();
                saveRecord();
                return;
            case 3:
                registerReceiver(new SmsReceiver(), new IntentFilter("android.net.wifi.STATE_CHANGE"));
                return;


        }
    }
}
