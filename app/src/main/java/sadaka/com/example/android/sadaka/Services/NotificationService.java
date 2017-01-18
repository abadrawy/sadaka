package sadaka.com.example.android.sadaka.Services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import sadaka.com.example.android.sadaka.R;

import sadaka.com.example.android.sadaka.activities.Organizations;

/**
 * Created by aisha on 1/18/2017.
 */
public class NotificationService extends IntentService{
    String TAG="NotificationService";
    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"onHandleIntent");

        addNotification();

    }
    private void addNotification() {

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_stat_drafts)
                            .setContentTitle("Donate")
                            .setContentText("Don't forget to help those in need");




        Intent notificationIntent = new Intent(this, Organizations.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int) System.currentTimeMillis(),notificationIntent,0);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        Log.d("NotificationService","notification was built");
    }
}
