package sadaka.com.example.android.sadaka.Recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import sadaka.com.example.android.sadaka.Services.NotificationService;

/**
 * Created by aisha on 1/11/2017.
 */
public class SmsReceiver extends BroadcastReceiver {
    Context context;
    final String TAG="SmsReciver";
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        Log.d(TAG,"start");

        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            Object []pdus=(Object[])bundle.get("pdus");
            String format = bundle.getString("format");
            SmsMessage[] message=new SmsMessage[pdus.length];
            for(int i=0;i<pdus.length;i++)
                message[i]=getIncomingMessage(pdus[i],bundle);
            if(message.length>=0) {
                Log.d(TAG, message[0].getMessageBody());
                if(message[0].getMessageBody().contains("credit")) {
                    Log.d(TAG,"has credit");
                    prepareNotification();;
                }
            }
            abortBroadcast();
        }
    }
    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            //if i want to support older versions

            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
    public  void prepareNotification(){
        //i should start a service that handles creation of notifcation
        Intent intent=new Intent(context, NotificationService.class);
        context.startService(intent);

    }
}
